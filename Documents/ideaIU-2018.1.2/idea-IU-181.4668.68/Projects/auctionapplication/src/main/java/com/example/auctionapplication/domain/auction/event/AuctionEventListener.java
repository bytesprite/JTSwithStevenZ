package com.example.auctionapplication.domain.auction.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@Component
public class AuctionEventListener {

    private Logger logger = LoggerFactory.getLogger(AuctionEventListener.class);


    @EventListener
    public void onAuctionCreatedEvent(AuctionCreatedEvent event){
        logger.info("Auction with id{} created", event.getSource().getId()); //NAME. ID is null so this is pointless
    }
}


//Todo
//  When a user fails to login 3 times we are going to notify the police (See bookmark)
//  Fuck around with aspects
//  Install Docker


//We will make another service entirely to consume the event


//potentially an issue with two events being called for the save method?