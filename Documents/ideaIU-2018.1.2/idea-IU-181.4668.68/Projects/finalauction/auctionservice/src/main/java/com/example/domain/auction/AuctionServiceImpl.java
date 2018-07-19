package com.example.domain.auction;

import com.example.domain.auction.event.AuctionCreatedEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuctionServiceImpl implements AuctionService{

    private ApplicationEventPublisher eventPublisher;

    private AuctionRepository auctionRepository;

    @Override
    public Auction save(Auction auction) {

        if(auction.getId() == null){
            eventPublisher.publishEvent(new AuctionCreatedEvent(auction));
        }

        return auctionRepository.save(auction);

    }

    @Override
    public Auction update(Long id, Auction inbound){
        Auction saved = (auctionRepository.findById(id).get());
        saved.setName(inbound.getName());
        saved.setDescription(inbound.getDescription());
        return auctionRepository.save(saved);
    }

    @Override
    public Iterable<Auction> findAll() {
        return auctionRepository.findAll();
    }

    @Override
    public Optional<Auction> findByID(Long id){
        return auctionRepository.findById(id);
    }

    @Override
    public void delete(Auction auction) {
        auctionRepository.delete(auction);
    }

    @Override
    public void deleteByID(Long id) {
        auctionRepository.deleteById(id);
    }


}

