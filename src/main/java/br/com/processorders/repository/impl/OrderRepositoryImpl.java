package br.com.processorders.repository.impl;

import br.com.processorders.api.dto.request.FilterOrderRequestDTO;
import br.com.processorders.domain.Order;
import br.com.processorders.repository.OrderRepositoryCustom;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderRepositoryImpl implements OrderRepositoryCustom {

   @Autowired
   private LocalContainerEntityManagerFactoryBean emf;

    @Transactional
    @Override
    public Page<Order> findOrdersByCriteria(FilterOrderRequestDTO filterOrderRequestDTO, Pageable pageable) {
        EntityManager em = emf.getNativeEntityManagerFactory().createEntityManager();
        StringBuilder jpql = new StringBuilder("SELECT o FROM Order o WHERE 1=1");
        Map<String, Object> parameters = new HashMap<>();

        preparaWhere(filterOrderRequestDTO, jpql, parameters);
        TypedQuery<Order> query = setParams(em, jpql, parameters);

        List<Order> resultList = query
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        String countJpql = jpql.toString().replace("SELECT o", "SELECT COUNT(o)");
        TypedQuery<Long> countQuery = setParamsLong(em, countJpql, parameters);
        Long count = countQuery.getSingleResult();

        return new PageImpl<>(resultList, pageable, count);
    }

    private static TypedQuery<Order> setParams(EntityManager em, StringBuilder jpql, Map<String, Object> parameters) {
        TypedQuery<Order> query = em.createQuery(jpql.toString(), Order.class);
        for (Map.Entry<String, Object> param : parameters.entrySet()) {
            query.setParameter(param.getKey(), param.getValue());
        }
        return query;
    }

    private static TypedQuery<Long> setParamsLong(EntityManager em, String countJpql, Map<String, Object> parameters) {
        TypedQuery<Long> query = em.createQuery(countJpql, Long.class);
        for (Map.Entry<String, Object> param : parameters.entrySet()) {
            query.setParameter(param.getKey(), param.getValue());
        }
        return query;
    }

    private static void preparaWhere(FilterOrderRequestDTO filterOrderRequestDTO, StringBuilder jpql, Map<String, Object> parameters) {
        if (filterOrderRequestDTO.getOrderReceived() != null) {
            jpql.append(" AND DATE(o.orderReceived) = :orderReceived");
            parameters.put("orderReceived", filterOrderRequestDTO.getOrderReceived().toLocalDate());
        }
        if (filterOrderRequestDTO.getSellerCode() != null) {
            jpql.append(" AND o.sellerCode = :sellerCode");
            parameters.put("sellerCode", filterOrderRequestDTO.getSellerCode());
        }
        if (!StringUtils.isBlank(filterOrderRequestDTO.getClienteDocument())) {
            jpql.append(" AND o.clienteDocument = :clienteDocument");
            parameters.put("clienteDocument", filterOrderRequestDTO.getClienteDocument());
        }
    }
}
