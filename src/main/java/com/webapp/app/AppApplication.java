package com.webapp.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.webapp.app.services.OrderProducer;
import com.webapp.app.services.UseNativeProducer;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableScheduling
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Bean 
	CommandLineRunner run (
		OrderProducer producer
		, UseNativeProducer nativeProducer ) {
		return args -> {
			producer.send("{\"orderId\":1001,\"customer\":\"Diogo\"}");
			nativeProducer.snedNativeProducer_1("{\"orderId\":1002,\"customer\":\"HELLO FROM RUST TO JAVAAAAA!!!!\"}");
		};
	}

}
