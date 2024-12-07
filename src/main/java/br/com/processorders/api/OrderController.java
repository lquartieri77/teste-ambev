package br.com.processorders.api;

import br.com.processorders.api.dto.request.FilterOrderRequestDTO;

import br.com.processorders.api.dto.response.GetOrdersResponseDTO;
import br.com.processorders.service.OrderService;
import br.com.processorders.utils.DateUtils;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Controller
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<GetOrdersResponseDTO> getFilteredOrders(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String orderReceived,
            @RequestParam(required = false) Integer sellerCode,
            @RequestParam(required = false) String clienteDocument,
            Pageable pageable
    ) {
        FilterOrderRequestDTO filtro = new FilterOrderRequestDTO();
        filtro.setOrderReceived(DateUtils.convertStringToLocalDateTime(orderReceived));
        filtro.setSellerCode(sellerCode);
        filtro.setClienteDocument(clienteDocument);
        GetOrdersResponseDTO responseDTO = orderService.getFilteredOrders(filtro, pageable);
        return ResponseEntity.ok(responseDTO);
    }

}
