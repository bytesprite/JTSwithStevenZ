package com.example.domain.auction.cron;

import com.example.domain.auction.Auction;
import com.example.domain.auction.AuctionService;
import com.example.domain.auction.event.AuctionCronBatchEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


@EnableScheduling()
@Configuration
public class AuctionCron {

    @Autowired
    private AuctionService auctionService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;


    //Every 10 Seconds
    @Scheduled(fixedDelay = 10000) //can't set this via config. Get an 'attribute Must be Constant message. Revisit'. //, cron = "0/10 * * * * *"
    public void AuctionCronJob(){

        Iterable<Auction> auctions = auctionService.findAll();

        eventPublisher.publishEvent(new AuctionCronBatchEvent(auctions));
    }
}
