package br.com.processorders.integration.rabbitmq.order.consumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConsumerProperties {

    @Value("${consumer.fixed-rate}")
    private long fixedRate;

    @Value("${consumer.max-records}")
    private int maxRecords;

    public long getFixedRate() {
        return fixedRate;
    }

    public int getMaxRecords() {
        return maxRecords;
    }
}