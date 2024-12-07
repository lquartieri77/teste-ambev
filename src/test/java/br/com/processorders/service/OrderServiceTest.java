package br.com.processorders.service;

import br.com.processorders.api.dto.request.FilterOrderRequestDTO;
import br.com.processorders.api.dto.response.GetOrdersResponseDTO;
import br.com.processorders.domain.Order;
import br.com.processorders.repository.OrderRepositoryCustom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepositoryCustom orderRepositoryCustom;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetFilteredOrdersWithData() {
        FilterOrderRequestDTO filterOrderRequestDTO = new FilterOrderRequestDTO();
        PageRequest pageable = PageRequest.of(0, 10);

        Order order = new Order();
        Page<Order> orderPage = new PageImpl<>(Collections.singletonList(order), pageable, 1);

        when(orderRepositoryCustom.findOrdersByCriteria(filterOrderRequestDTO, pageable)).thenReturn(orderPage);

        GetOrdersResponseDTO result = orderService.getFilteredOrders(filterOrderRequestDTO, pageable);

        assertEquals(1, result.getListaDeOrders().getTotalElements());
        assertEquals(null, result.getListaDeOrders().getContent().get(0));
    }

    @Test
    public void testGetFilteredOrdersNoData() {
        FilterOrderRequestDTO filterOrderRequestDTO = new FilterOrderRequestDTO();
        PageRequest pageable = PageRequest.of(0, 10);

        Page<Order> emptyOrderPage = new PageImpl<>(Collections.emptyList(), pageable, 0);

        when(orderRepositoryCustom.findOrdersByCriteria(filterOrderRequestDTO, pageable)).thenReturn(emptyOrderPage);

        GetOrdersResponseDTO result = orderService.getFilteredOrders(filterOrderRequestDTO, pageable);

        assertEquals(0, result.getListaDeOrders().getTotalElements());
        assertEquals(Collections.emptyList(), result.getListaDeOrders().getContent());
    }
}
