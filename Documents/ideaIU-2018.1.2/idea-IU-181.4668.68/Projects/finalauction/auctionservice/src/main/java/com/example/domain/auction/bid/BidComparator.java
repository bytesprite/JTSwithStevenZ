package com.example.domain.auction.bid;

import java.util.Comparator;

public class BidComparator implements Comparator<Bid> {

    @Override
    public int compare(Bid b1, Bid b2) {
        // multiple by -1 to reverse order
        return Double.compare(b1.getAmount().doubleValue(), b2.getAmount().doubleValue()) * -1;
    }
}