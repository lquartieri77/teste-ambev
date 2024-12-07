package br.com.processorders.repository;

import br.com.processorders.api.dto.request.FilterOrderRequestDTO;
import br.com.processorders.domain.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import br.com.processorders.repository.impl.OrderRepositoryImpl;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class OrderRepositoryImplTest {

    @InjectMocks
    private OrderRepositoryImpl orderRepository;

    @Mock
    private LocalContainerEntityManagerFactoryBean emf;

    @Mock
    private EntityManagerFactory entityManagerFactory;

    @Mock
    private EntityManager em;

    @Mock
    private TypedQuery<Order> query;

    @Mock
    private TypedQuery<Long> countQuery;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(emf.getNativeEntityManagerFactory()).thenReturn(entityManagerFactory);
        when(entityManagerFactory.createEntityManager()).thenReturn(em);
    }

    @Test
    public void testFindOrdersByCriteria() {
        FilterOrderRequestDTO filterOrderRequestDTO = new FilterOrderRequestDTO();
        PageRequest pageable = PageRequest.of(0, 10);

        Order order = new Order();
        List<Order> orderList = Collections.singletonList(order);
        Page<Order> orderPage = new PageImpl<>(orderList, pageable, 1L);

        when(em.createQuery(anyString(), eq(Order.class))).thenReturn(query);
        when(query.setFirstResult(anyInt())).thenReturn(query);
        when(query.setMaxResults(anyInt())).thenReturn(query);
        when(query.getResultList()).thenReturn(orderList);

        when(em.createQuery(anyString(), eq(Long.class))).thenReturn(countQuery);
        when(countQuery.getSingleResult()).thenReturn(1L);

        Page<Order> result = orderRepository.findOrdersByCriteria(filterOrderRequestDTO, pageable);

        assertEquals(orderPage.getTotalElements(), result.getTotalElements());
        assertEquals(orderPage.getContent(), result.getContent());
    }

    @Test
    public void testFindOrdersByCriteriaNoData() {
        FilterOrderRequestDTO filterOrderRequestDTO = new FilterOrderRequestDTO();
        PageRequest pageable = PageRequest.of(0, 10);

        when(em.createQuery(anyString(), eq(Order.class))).thenReturn(query);
        when(query.setFirstResult(anyInt())).thenReturn(query);
        when(query.setMaxResults(anyInt())).thenReturn(query);
        when(query.getResultList()).thenReturn(Collections.emptyList());

        when(em.createQuery(anyString(), eq(Long.class))).thenReturn(countQuery);
        when(countQuery.getSingleResult()).thenReturn(0L);

        Page<Order> result = orderRepository.findOrdersByCriteria(filterOrderRequestDTO, pageable);

        assertEquals(0, result.getTotalElements());
        assertEquals(Collections.emptyList(), result.getContent());
    }
}
