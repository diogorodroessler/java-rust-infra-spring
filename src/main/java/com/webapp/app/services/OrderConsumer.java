package com.webapp.app.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component 
public class OrderConsumer {

    private final OrderService service;

    public OrderConsumer(OrderService service) {
        this.service = service;
    }

    @KafkaListener(topics = "orders")
    public void consume(String message) {
        System.out.println("Message Kafka consume recebida: " + message);

        service.save(message);

        System.out.println("Salvado em Sql Server");
    }
}
