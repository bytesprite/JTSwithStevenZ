package com.example.auctionapplication.domain.auction.event;


import com.example.auctionapplication.domain.auction.Auction;
import org.springframework.context.ApplicationEvent;

public class AuctionCreatedEvent extends ApplicationEvent {

    public AuctionCreatedEvent(Auction source) {
        super(source);
    }

    @Override
    public Auction getSource(){
        return (Auction)source;
    }
}
