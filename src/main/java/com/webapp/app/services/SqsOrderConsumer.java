package com.webapp.app.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;

@Component
public class SqsOrderConsumer {

    private final SqsClient sqsClient;
    private final OrderService orderService;
    private final String queueUrl;

    public SqsOrderConsumer(
            SqsClient sqsClient,
            OrderService orderService,
            @Value("${aws.sqs.queue-url}") String queueUrl
        ) {
        this.sqsClient = sqsClient;
        this.orderService = orderService;
        this.queueUrl = queueUrl;
    }

    @Scheduled(fixedDelayString = "${aws.sqs.poll-delay-ms:2000}")
    public void pollQueue() {
        var response = sqsClient.receiveMessage(
                ReceiveMessageRequest.builder()
                        .queueUrl(queueUrl)
                        .maxNumberOfMessages(5)
                        .waitTimeSeconds(10)
                        .build()
                    );

        for (Message message : response.messages()) {
            try {
                System.out.println("Mensagem SQS recebida: " + message.body());

                orderService.save(message.body());

                sqsClient.deleteMessage(
                        DeleteMessageRequest.builder()
                                .queueUrl(queueUrl)
                                .receiptHandle(message.receiptHandle())
                                .build()
                            );

                System.out.println("Mensagem SQS salva no SQL Server e removida da fila.");
            } catch (Exception e) {
                System.err.println(
                        "Falha ao processar mensagem SQS; ela não será removida: "
                                + e.getMessage()
                            );
            }
        }
    }
}
