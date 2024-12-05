package br.com.processorders.repository;

import br.com.processorders.api.dto.request.FilterOrderRequestDTO;
import br.com.processorders.domain.Order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface OrderRepositoryCustom {
    Page<Order> findOrdersByCriteria(FilterOrderRequestDTO filterOrderRequestDTO, Pageable pageable);
}
