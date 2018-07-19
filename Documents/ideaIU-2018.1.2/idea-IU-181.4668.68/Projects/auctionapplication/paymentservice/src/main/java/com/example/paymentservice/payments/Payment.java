package com.example.paymentservice.payments;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Payment extends AbstractEntity {

    private BigDecimal amount;

    private Long userID;

    private String auctionName;

    private String auctionDescription;

}
