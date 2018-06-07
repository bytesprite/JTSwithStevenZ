package com.example.paymentservice.rabbitmq;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentReceiver {

    private static final Logger log = LoggerFactory.getLogger(PaymentReceiver.class);

    @RabbitListener(queues="rfq")
    public void recievedMessage(String msg) {
        log.info("Received Message: " + msg);
    }
}
