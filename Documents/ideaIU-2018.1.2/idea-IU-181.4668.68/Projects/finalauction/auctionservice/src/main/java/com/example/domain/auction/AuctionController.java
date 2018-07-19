package com.example.domain.auction;

import com.example.domain.auction.validation.AuctionValidator;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class AuctionController {

    @InitBinder
    public void init(WebDataBinder web){
        web.addValidators(new AuctionValidator());
    }

    @NonNull
    private  AuctionService auctionService;

    @PostMapping("/auctions")
    public Auction save(@Valid @RequestBody Auction auction){
        return auctionService.save(auction);
    }

    //http -a user1:1  POST localhost:8080/auctions name="testAuction" description="test"


    @PutMapping("/auctions/{id}")
    public Auction update(@PathVariable("id") Long id, @RequestBody Auction auction) {
        return auctionService.update(id, auction);
    }

    //http -a user1:1  PUT localhost:8080/auctions/1 name="testAuction" description="test1"

    @DeleteMapping("/auctions/{id}")
    public void deleteAuctionsByID(@PathVariable("id") Long id){
        auctionService.deleteByID(id);
    }

    @DeleteMapping("/auctions")
    public void deleteAuctions(@RequestBody Auction auction){
        auctionService.delete(auction);
    }

    @GetMapping("/auctions")
    public Iterable<Auction> getAllAuctions(){
        Iterable<Auction> iterable = auctionService.findAll();
        if(iterable != null) {return  iterable;}
        else return null;

    }

    @GetMapping("/auctions/{id}")
    public Auction getAuctionByID(@PathVariable("id") Long id){
        Optional<Auction> auction = auctionService.findByID(id);
        return auction.orElse(null);
    }
}

// http -a user1:password  POST localhost:8080/auctions name="testAuction" description="test"
// http DELETE localhost:8080/auctions/id