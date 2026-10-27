package com.webapp.app.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.webapp.app.NativeEngine;

@Component 
public class UseNativeConsumer {
    private final OrderService orderService;
    private final NativeEngine nativeEngine;

    public UseNativeConsumer(
        OrderService orderService,
        NativeEngine nativeEngine
    ) {
        this.orderService = orderService;
        this.nativeEngine = nativeEngine;
    }

    @KafkaListener(topics = "orders-by-rust")
    public void consumeNativeLibrary_1(String message) {
        System.out.println("Kafka Consume Rust how Native library recebida: " + message);
        
        String result = NativeEngine.hello(message);
        System.out.println("RESULT FROM RUST TO JAVA: " + result);
        
        orderService.save(result);
    }
}
