package com.example.auctionapplication.domain.auction;

import com.example.auctionapplication.domain.AbstractEntity;
import com.example.auctionapplication.domain.auction.bid.Bid;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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

    @JsonIgnore
    @OneToMany(mappedBy = "auction", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bid> bids = new ArrayList<>();

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

    public Auction removeBid(Bid bid){
        bids.remove(bid);
        bid.setAuction(null);

        return this;
    }
}
