package com.example.auctionapplication.domain.auction.event;


import com.example.auctionapplication.domain.auction.Auction;
import com.example.auctionapplication.domain.auction.bid.Bid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class CronBatchListener {

    private Logger logger = LoggerFactory.getLogger(CronBatchListener.class);

    @Autowired
    private ApplicationEventPublisher eventPublisher;


    @EventListener
    public void onAuctionCronBatchEvent(AuctionCronBatchEvent event){

        LocalDateTime currentTime = LocalDateTime.now();

        event.getSource().forEach(auction -> {
            if(Duration.between(auction.getCreationDate(), currentTime).toMillis() > 10000 && auction.getIsOpen()) { //Duration.between(currentTime, auction.getCreationDate()).toMillis()
                Optional<Bid> bidOptional = auction.getHighestBid();

                if(bidOptional.isPresent()){
                    logger.info("AuctionID:'{}' Concluded through BidID:'{}'. Firing AuctionConcludedEvent", auction.getId(), bidOptional.get().getId());
                    eventPublisher.publishEvent(new AuctionConcludedEvent(bidOptional.get()));
                }
                else{
                    logger.info("AuctionID:'{}' Expired. Firing 'AuctionExpiredEvent.'", auction.getId());
                    eventPublisher.publishEvent(new AuctionExpiredEvent(auction));
                }
            }
//            else{logger.info("The Current Time is {}", (Duration.between(auction.getCreationDate(), currentTime).toMillis()));}
        });
    }

}
