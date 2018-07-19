package com.example.domain.auction.bid;

import com.example.domain.auction.Auction;
import com.example.domain.auction.AuctionRepository;
import com.example.domain.auction.bid.rule.BidRuleEngine;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class BidServiceImpl implements  BidService{

    BidRepository bidRepo;
    AuctionRepository aucRepo;
    BidRuleEngine bidRuleEngine;


    @Override
    public Iterable<Bid> findAllBidsByAuction(Auction auction) {

        Iterable<Bid> allBids = bidRepo.findAll();
        List<Bid> matchingAuctionBids = new ArrayList<>();
        Long auctionId = auction.getId();


        allBids.forEach(bid -> {if(bid.getAuction().getId().equals(auctionId)) matchingAuctionBids.add(bid);});

        return matchingAuctionBids;
    }

    @Override
    public Iterable<Bid> findAllByAuctionId(Long auctionId) {
        Iterable<Bid> allBids = bidRepo.findAll();
        List<Bid> matchingAuctionBids = new ArrayList<>();


        allBids.forEach(bid -> {if(bid.getAuction().getId().equals(auctionId)) matchingAuctionBids.add(bid);});

        return matchingAuctionBids;
    }

    @Override
    public Optional<Bid> findBidById(Long id) {
        return bidRepo.findById(id);
    }

    @Override
    public Bid save(Auction auction, Bid bid) {

        executeRules(auction, bid);

        Bid savedBid = bidRepo.save(bid);

        aucRepo.save(auction.addBid(savedBid));

        return bid;
    }

    @Override
    public Bid update(Auction auction, Bid current, Bid incoming) {
        current.setAmount(incoming.getAmount());

        aucRepo.save(auction.addBid(current));

        return current;
    }

    @Override
    public void delete(Auction auction, Bid bid) {
        aucRepo.save(auction.removeBid(bid));
    }

    @Override
    public void deleteById(Auction auction, Long id) {
        Optional<Bid> bid = bidRepo.findById(id);

        if(bid.isPresent()){
            aucRepo.save(auction.removeBid(bid.get()));
        }
    }

    private void executeRules(Auction auction, Bid bid) {
        Map<Object, Object> context = new HashMap<>();
        context.put("auction", auction);
        bidRuleEngine.execute(bid, context);
    }
}
