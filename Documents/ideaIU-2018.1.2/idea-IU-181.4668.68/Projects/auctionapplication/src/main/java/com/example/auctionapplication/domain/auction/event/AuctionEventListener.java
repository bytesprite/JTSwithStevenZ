package com.example.auctionapplication.domain.auction.event;

import com.example.auctionapplication.domain.auction.Auction;
import com.example.auctionapplication.domain.auction.AuctionRepository;
import com.example.auctionapplication.domain.auction.bid.Bid;
import com.example.auctionapplication.domain.auction.rabbitmq.Producer;
import jdk.nashorn.api.scripting.JSObject;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.time.LocalDateTime;

@Component
public class AuctionEventListener {

    private Logger logger = LoggerFactory.getLogger(AuctionEventListener.class);

    @Autowired
    AuctionRepository auctionRepository;

    @Autowired
    private Producer producer;


    @EventListener
    public void onAuctionCreatedEvent(AuctionCreatedEvent event){
        event.getSource().setCreationDate(LocalDateTime.now());
        event.getSource().setIsOpen(true);
        logger.info("Auction with id{} created", event.getSource().getId()); //NAME. ID is null so this is pointless
    }

    @EventListener
    public void onAuctionConcludedEvent(AuctionConcludedEvent event) throws JSONException {
        Bid winningBid = event.getSource();
        Auction concludedAuction = winningBid.getAuction();
        logger.info("BidID:'{}' for AuctionID:'{}' won. Sending to OrderProcessing & Closing Auction.", winningBid.getId(), concludedAuction.getId());
        concludedAuction.setIsOpen(false);//can break these out
        auctionRepository.save(concludedAuction);

        JSONObject message = new JSONObject();
        message.put("amount", winningBid.getAmount());
        message.put("userID", winningBid.getCreatedBy());
        message.put("auctionName", concludedAuction.getName());
        message.put("auctionDescription", concludedAuction.getDescription());

        producer.produceMsg(message);

    }

    @EventListener
    public void onAuctionExpiredEvent(AuctionExpiredEvent event){
        Auction bidlessAuction = event.getSource();
        logger.info("AuctionID:'{}' Expired. Closing Auction", bidlessAuction.getId());
        bidlessAuction.setIsOpen(false);//can break these out
        auctionRepository.save(bidlessAuction);
    }
}


//Todo
//  When a user fails to login 3 times we are going to notify the police (See bookmark)
//  Fuck around with aspects
//  Install Docker


//We will make another service entirely to consume the event


//potentially an issue with two events being called for the save method?