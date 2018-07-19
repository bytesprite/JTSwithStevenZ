package com.example.paymentservice.rabbitmq;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Component
public class PaymentReceiver {
    Logger logger = LoggerFactory.getLogger(PaymentReceiver.class);

    public void receiveMessage(String message) {
        logger.info("RabbitMQ Message: '{}'", message);
    }

}
