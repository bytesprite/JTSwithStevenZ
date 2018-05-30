package com.example.auctionapplication.domain.auction;

import java.util.Optional;

public interface AuctionService {

    Auction save(Auction auction);

    Auction update(Long id, Auction auction);

    Iterable<Auction> findAll();

    Optional<Auction> findByID(Long id);

    void delete(Auction auction);

    void deleteByID(Long id);

}
