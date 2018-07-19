package com.example.domain.auction.bid;

import com.example.domain.AbstractEntity;
import com.example.domain.auction.Auction;
import com.example.domain.auction.bid.validation.constraint.BidIncrement;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Entity
@Data
@ToString(exclude = "auction")
@EqualsAndHashCode(exclude = "auction")
public class Bid extends AbstractEntity {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @NotNull
    @BidIncrement
    private BigDecimal amount;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
    private Auction auction;


}