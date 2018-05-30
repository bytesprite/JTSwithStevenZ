package com.example.auctionapplication.domain.auction.bid.rule;

import com.example.auctionapplication.auditing.context.UserContextService;
import com.example.auctionapplication.domain.auction.bid.Bid;
import com.example.auctionapplication.domain.auction.validation.rule.SimpleRuleEngine;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@AllArgsConstructor
public class BidRuleEngine extends SimpleRuleEngine<Bid> {

    private UserContextService userContextService;

    @PostConstruct
    void init(){
        addRule(new BidMoreThanZeroRule(userContextService), false);
        addRule(new BidOnceRule(userContextService), false);
        addRule(new BidGreaterThanMax(userContextService),false);
        addRule(new LongUserName(userContextService), true);
    }

}