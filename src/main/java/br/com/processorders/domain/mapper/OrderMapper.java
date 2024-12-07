package br.com.processorders.domain.mapper;

import br.com.processorders.api.dto.response.OrderDTO;
import br.com.processorders.domain.Order;
import br.com.processorders.domain.mapper.ProductMapper;

import java.math.BigDecimal;
import java.util.stream.Collectors;


public class OrderMapper {

    public static OrderDTO toDto(Order order) {
        if (order == null || order.getProductList() == null) {
            return null;
        }

        return OrderDTO.builder()
                .productList(order.getProductList().stream()
                        .map(ProductMapper::toDto)
                        .collect(Collectors.toList()))
                .orderDate(order.getOrderDate())
                .orderReceived(order.getOrderReceived())
                .clientName(order.getClientName())
                .clienteDocument(order.getClienteDocument())
                .sellerCode(order.getSellerCode())
                .valorTotal(calculaValorTotal(order))
                .build();
    }

    private static BigDecimal calculaValorTotal(Order order) {
        return order.getProductList().stream()
                .map(product -> product.getValue())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
