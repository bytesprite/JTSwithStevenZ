package com.example.paymentservice.rabbitmq;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig{
        @Bean
        public PaymentReceiver receiver() {
            return new PaymentReceiver();
        }
}
