package br.com.processorders.controller;

import br.com.processorders.api.OrderController;
import br.com.processorders.api.dto.request.FilterOrderRequestDTO;
import br.com.processorders.api.dto.response.GetOrdersResponseDTO;
import br.com.processorders.api.dto.response.OrderDTO;
import br.com.processorders.domain.Order;
import br.com.processorders.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class OrderControllerTest {

    @InjectMocks
    private OrderController orderController;

    @Mock
    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllOrders() {
        FilterOrderRequestDTO filterOrderRequestDTO = new FilterOrderRequestDTO();
        PageRequest pageable = PageRequest.of(0, 10);
        OrderDTO order = OrderDTO.builder().build();
        Page<OrderDTO> orderPage = new PageImpl<>(Collections.singletonList(order), pageable, 1);
        GetOrdersResponseDTO res = GetOrdersResponseDTO.builder().listaDeOrders(orderPage).build();

        when(orderService.getFilteredOrders(filterOrderRequestDTO, pageable)).thenReturn(res);
        ResponseEntity<GetOrdersResponseDTO> response = orderController.getFilteredOrders("24/12/2024",322, "12312313",pageable);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
