package com.example.paymentservice.payments;

import java.util.Optional;

public interface PaymentService {

    Payment save(Payment payment);

    Payment update(Long id, Payment payment);

    Iterable<Payment> findAll();

    Optional<Payment> findByID(Long id);

    void delete(Payment payment);

    void deleteByID(Long id);


}
