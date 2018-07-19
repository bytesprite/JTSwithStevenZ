package com.example.paymentservice.payments;

import com.example.paymentservice.events.PaymentCreatedEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService{

    private PaymentRepository paymentRepository;

    private ApplicationEventPublisher eventPublisher;

    @Override
    public Payment save(Payment payment){
        if(payment.getId() == null){
            eventPublisher.publishEvent(new PaymentCreatedEvent(payment));
        }
        return paymentRepository.save(payment);
    }

    @Override
    public Payment update(Long id, Payment payment) {
        return null;
    }

    @Override
    public Iterable<Payment> findAll() {
        return null;
    }

    @Override
    public Optional<Payment> findByID(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Payment payment) {

    }

    @Override
    public void deleteByID(Long id) {

    }

}
