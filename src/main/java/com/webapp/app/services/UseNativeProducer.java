package com.webapp.app.services;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service 
public class UseNativeProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public UseNativeProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void snedNativeProducer_1(String message) {
        kafkaTemplate.send("orders-by-rust", message);
    }

}
