package br.com.processorders.schedule;

import br.com.processorders.integration.rabbitmq.order.consumer.OrderConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledDigestMessages {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledDigestMessages.class);
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private OrderConsumer orderConsumer;

    @Value("${consumer.fila}")
    private String queueName;


    //@Scheduled(fixedRateString = "${consumer.fixed-rate}")
    public void consumeSingleOrderMessage() {
        // exemplo de order = src/main/resources/single_order_message.json
        logger.info(">> Iniciando processamento agendado - consumeSingleOrderMessage");
        Message message = rabbitTemplate.receive(queueName);
        if (message != null) {
            logger.info(">> Chamando orderConsumer.receiveMessage...");
            orderConsumer.receiveOrder(message);
            return;
        }
        logger.info(">> Sem mensagens novas para processar...");

    }

    @Scheduled(fixedRateString = "${consumer.fixed-rate}")
    public void consumeOrdersListMessages() {
        // exemplo de lista de orders = src/main/resources/order_list_message.json
        logger.info(">> Iniciando processamento agendado - consumeOrdersListMessages");
        Message message = rabbitTemplate.receive(queueName);
        if (message != null) {
            logger.info(">> Chamando orderConsumer.receiveMessage...");
            orderConsumer.receiveOrderList(message);
            return;
        }
        logger.info(">> Sem mensagens novas para processar...");
    }
}
