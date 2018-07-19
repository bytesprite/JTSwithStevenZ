package com.example.domain.auction.bid.rule;

import com.example.auditing.context.UserContextService;
import com.example.domain.auction.bid.Bid;
import com.example.domain.auction.validation.rule.Rule;
import lombok.AllArgsConstructor;
import org.springframework.validation.Errors;

import java.util.Map;

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
