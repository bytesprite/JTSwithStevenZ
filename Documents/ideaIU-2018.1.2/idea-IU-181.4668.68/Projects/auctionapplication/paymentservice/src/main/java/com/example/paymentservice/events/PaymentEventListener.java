package com.example.paymentservice.events;

import com.example.paymentservice.payments.Payment;
import com.example.paymentservice.payments.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentEventListener {

    @Autowired
    PaymentRepository paymentRepository;

    private Logger logger = LoggerFactory.getLogger(PaymentEventListener.class);


    @EventListener
    public void onPaymentCreatedEvent(PaymentCreatedEvent event){
        Payment p = event.getSource();
        logger.info("PaymentID:'{}' has been created on behalf of UserID:'{}' for Auction:'{}'", p.getId(), p.getUserID(), p.getAuctionName());
    }
}
