package com.example.auctionapplication.domain.auction.bid;

import com.example.auctionapplication.domain.auction.Auction;

import java.util.Optional;

public interface BidService {

    Iterable<Bid> findAllBidsByAuction(Auction auction);//remove bid
    Iterable<Bid> findAllByAuctionId(Long auctionId);
    Optional<Bid> findBidById(Long id);
    Bid save(Auction auction, Bid bid);
    Bid update(Auction auction, Bid incoming, Bid current);
    void delete(Auction auction, Bid bid);
    void deleteById(Auction auction, Long id);
}
