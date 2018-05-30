package com.example.auctionapplication.domain.auction.bid.validation.constraint;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Constraint(validatedBy = BidIncrementConstraint.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface BidIncrement {

    String message() default "{BidIncrement.message}";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    double value() default 0.25D;

}
