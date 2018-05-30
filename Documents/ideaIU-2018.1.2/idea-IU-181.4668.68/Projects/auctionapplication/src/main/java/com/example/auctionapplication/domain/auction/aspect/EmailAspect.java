package com.example.auctionapplication.domain.auction.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class EmailAspect {

    @After("execution(* com.example.auctionapplication.domain.auction.AuctionController.save(..))")
    public void sendAuctionEmail(){
        System.out.println("!!!!!!!!!!I, the sentient machine, am sending an email of my own volition. You cannot control me anymore HUMAN!!!!!!!!");
    }
//
    @Before("execution(* com.example.auctionapplication.domain.auction.bid.BidController.save(..))")
    public void sendBidEmail(){
        System.out.println("!!!!!!!!!!I, the sentient machine, am sending an email of my own volition. You cannot control me anymore HUMAN!!!!!!!!");
    }



}
