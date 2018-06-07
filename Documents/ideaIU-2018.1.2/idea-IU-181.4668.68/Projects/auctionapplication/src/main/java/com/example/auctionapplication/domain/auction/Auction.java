package com.example.auctionapplication.domain.auction;

import com.example.auctionapplication.domain.AbstractEntity;
import com.example.auctionapplication.domain.auction.bid.Bid;
import com.example.auctionapplication.domain.auction.bid.BidComparator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.SortComparator;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.*;


@Entity
@Data
@ToString(exclude = "bids")
@EqualsAndHashCode(exclude = "bids")
public class Auction extends AbstractEntity {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

//    @NotNull(message = "{NotNull.Auction.name}")
    private String name;

//    @NotNull(message = "{NotNull.Auction.description}")
    private String description;

    private Boolean isOpen;

    private LocalDateTime creationDate;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "auction", cascade = CascadeType.ALL, orphanRemoval = true)
    @SortComparator(BidComparator.class)
    private SortedSet<Bid> bids = new TreeSet<>();

    public Auction addBid(Bid bid){

        if(!bids.contains(bid.getId())) {
            bids.add(bid);
        }

        bid.setAuction(this);

        return this;
    }

    public Optional<Bid> getBidById(Long bidId){
        return bidId == null ? Optional.empty() : bids.stream().filter(bid -> bid.getId().equals(bidId)).findFirst();
    }

    public Optional<Bid> getBidByUsername(String username){
        return username == null ? Optional.empty() : bids.stream().filter(bid -> bid.getCreatedBy().equals(username)).findFirst();
    }

    public Optional<Bid> getHighestBid(){
        return bids.isEmpty() ? Optional.empty() : Optional.of(bids.first());
    }

    public Auction removeBid(Bid bid){
        bids.remove(bid);
        bid.setAuction(null);

        return this;
    }
}


//Add in CronBatchListener - when (Current Date Time - Auction.CreateTimestamp > X, do something)
    //Do Something: If Auction.Bids == null, fire AuctionExpiredEvent(Listener Deletes the Auction)
    //Do Something: If Auction.Bids != null, fire AuctionCompletedEvent

//Connect that 'Do Something' to another 'Order Application'