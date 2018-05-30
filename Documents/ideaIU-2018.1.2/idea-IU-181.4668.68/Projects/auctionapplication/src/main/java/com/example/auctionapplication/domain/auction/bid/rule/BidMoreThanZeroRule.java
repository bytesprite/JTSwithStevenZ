package com.example.auctionapplication.domain.auction.bid.rule;

import com.example.auctionapplication.auditing.context.UserContextService;
import com.example.auctionapplication.domain.auction.Auction;
import com.example.auctionapplication.domain.auction.bid.Bid;
import com.example.auctionapplication.domain.auction.validation.rule.Rule;
import lombok.AllArgsConstructor;
import org.springframework.validation.Errors;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
public class BidMoreThanZeroRule implements Rule<Bid> {
    private UserContextService userContextService;

    @Override
    public void execute(Bid target, Map<Object, Object> context, Errors errors) {
        if( target.getAmount().intValue() <= 0 ){
            errors.reject("amount", new Object[]{target.getAmount()}, "{BidNotEnough");
        }
    }
}
