package br.com.processorders.service;

import br.com.processorders.api.dto.request.FilterOrderRequestDTO;
import br.com.processorders.api.dto.response.GetOrdersResponseDTO;
import br.com.processorders.api.dto.response.OrderDTO;
import br.com.processorders.domain.Order;

import br.com.processorders.domain.mapper.OrderMapper;
import br.com.processorders.repository.OrderRepository;
import br.com.processorders.repository.OrderRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderRepositoryCustom orderRepositoryCustom;


    public GetOrdersResponseDTO getFilteredOrders(FilterOrderRequestDTO filterOrderRequestDTO, Pageable pageable) {
        Page<Order> listaRetorno = orderRepositoryCustom.findOrdersByCriteria(filterOrderRequestDTO, pageable);

        return GetOrdersResponseDTO.builder().listaDeOrders(listaRetorno.map(OrderMapper::toDto)).dataHoraResposta(LocalDateTime.now()).build();
    }

    public List<Order> filtrarLista(List<Order> listaOriginal){
        // aqui viriam verificacoes se ja existe no banco de dados
        return listaOriginal.stream().distinct().collect(Collectors.toList());
    }

}
