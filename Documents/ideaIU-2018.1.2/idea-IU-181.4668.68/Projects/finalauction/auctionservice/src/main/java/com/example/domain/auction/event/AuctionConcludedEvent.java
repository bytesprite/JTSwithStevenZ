package com.example.domain.auction.event;

import com.example.domain.auction.bid.Bid;
import org.springframework.context.ApplicationEvent;

public class AuctionConcludedEvent extends ApplicationEvent {
    public AuctionConcludedEvent(Bid source) {
        super(source);
    }

    @Override
    public Bid getSource(){
        return (Bid)source;
    }
}
