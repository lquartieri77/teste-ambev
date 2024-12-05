package br.com.processorders.integration.rabbitmq.order.consumer;

import br.com.processorders.domain.Order;
import br.com.processorders.repository.OrderRepository;
import br.com.processorders.service.OrderService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class OrderConsumer {

    private static final Logger logger = LoggerFactory.getLogger(OrderConsumer.class);

    private final OrderRepository orderRepository;

    private final OrderService orderService;

    private final ObjectMapper objectMapper;

    @Autowired
    public OrderConsumer(OrderRepository orderRepository, OrderService orderService) {
        this.orderRepository = orderRepository;
        this.orderService = orderService;
        this.objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public void receiveOrder(Message message) {
        logger.info(">> OrderConsumer - receiveOrder");
        try {
            String messageContent = new String(message.getBody());
            Order order = objectMapper.readValue(messageContent, Order.class);
            order.setOrderReceived(LocalDateTime.now());
            orderRepository.save(order);
            logger.info("Order processada com sucesso: {}", order);
        } catch (Exception e) {
            logger.error("Erro ao processar order ", e);
        }

    }

    public void receiveOrderList(Message message) {
        logger.info(">> OrderConsumer - receiveOrderList");
        try {
            String messageContent = new String(message.getBody());
            List<Order> orderList = objectMapper.readValue(messageContent, new TypeReference<List<Order>>() {});

            List<Order> listaFiltradaSemRepeticoes = orderService.filtrarLista(orderList);

            listaFiltradaSemRepeticoes.forEach(order -> {
                order.setOrderReceived(LocalDateTime.now());
                orderRepository.save(order);
                logger.info("Order processada com sucesso: {}", order);
            });

        } catch (Exception e) {
            logger.error("Erro ao processar order ", e);
        }

    }

}
