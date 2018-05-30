package com.example.auctionapplication.domain.auction.bid;

import com.example.auctionapplication.domain.auction.Auction;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class BidController {

    @NonNull
    private  BidService bidService;

    @PostMapping("/auctions/{id}/bids")
    public ResponseEntity<Bid> save(@PathVariable("id") Optional<Auction> auction, @RequestBody Bid bid){

        return (auction.isPresent()) ?
                new ResponseEntity<>(bidService.save(auction.get(), bid), HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    // http -a user1:1 POST localhost:8080/auctions name="testAuction" description="test"
    // http -a user1:1 POST localhost:8080/auctions/1/bids "amount"="100"

    @GetMapping("/auctions/{id}/bids")
    public ResponseEntity<Iterable<Bid>> findAllBidsByAuction(@PathVariable("id") Optional<Auction> auction){
        return auction.isPresent() ?
                new ResponseEntity<>(bidService.findAllBidsByAuction(auction.get()), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    // http -a user1:1 GET localhost:8080/auctions/1/bids

    @GetMapping("/auctions/{auctionId}/bids/{bidId}")
    public ResponseEntity<Bid> findBidById(@PathVariable("auctionId") Optional<Auction> auction, @PathVariable("bidId") Optional<Bid> bid){
        return (auction.isPresent() & bid.isPresent()) ?
                new ResponseEntity<>(bid.get(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    // http -a user1:1 GET localhost:8080/auctions/1/bids/1

    @PutMapping("/auctions/{auctionId}/bids/{bidId}")
    public ResponseEntity<Bid> update(@PathVariable("auctionId") Optional<Auction> auction,
                                      @PathVariable("bidId") Optional<Bid> current,
                                      @RequestBody Bid incoming){
        return (auction.isPresent() && current.isPresent()) ?
                new ResponseEntity<>(bidService.update(auction.get(), current.get(), incoming), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    // http -a user1:1 PUT localhost:8080/auctions/1/bids/1 "amount"="999"

    @DeleteMapping("/auctions/{auctionId}/bids/{bidId}")
    public ResponseEntity delete(@PathVariable("auctionId") Optional<Auction> auction, @PathVariable("bidId") Optional<Bid> bid){
        if(auction.isPresent() && bid.isPresent()){

            bidService.delete(auction.get(), bid.get());

            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    // http -a user1:1 DELETE localhost:8080/auctions/1/bids/1
}


//http -a user:9c6fe92c-0f36-4685-96d2-437fde30bb3b POST localhost:8080/auctions name="testAuction" description="test2"