package com.example.domain.auction.event;

import com.example.domain.auction.Auction;
import org.springframework.context.ApplicationEvent;

public class AuctionCronBatchEvent extends ApplicationEvent {

    public AuctionCronBatchEvent(Iterable<Auction> source) {
        super(source);
    }

    @Override
    public Iterable<Auction> getSource(){
        return (Iterable<Auction>) source;
    }
}
