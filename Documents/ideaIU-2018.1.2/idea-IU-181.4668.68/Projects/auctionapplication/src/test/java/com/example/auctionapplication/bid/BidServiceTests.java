package com.example.auctionapplication.bid;



import com.example.auctionapplication.domain.auction.Auction;
import com.example.auctionapplication.domain.auction.AuctionRepository;
import com.example.auctionapplication.domain.auction.bid.Bid;
import com.example.auctionapplication.domain.auction.bid.BidRepository;
import com.example.auctionapplication.domain.auction.bid.BidServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BidServiceTests {

    @Mock
    BidRepository bidRepository;

    @Mock
    AuctionRepository auctionRepository;

    @InjectMocks
    BidServiceImpl bidService;


    Auction auction1 = new Auction();
    Bid bid1 = new Bid();
    Bid bid2 = new Bid();

    @Before
    public void before(){

        auction1.setId(1L);
        bid1.setId(1L);
        bid1.setAmount(BigDecimal.valueOf(50));
        bid1.setAuction(auction1);

    }

    @Test
    public void findByID() throws Exception {
        when(bidRepository.findById(bid1.getId())).thenReturn(java.util.Optional.ofNullable(bid1));
        assertThat(bidService.findBidById(bid1.getId()));
    }

    @Test
    public void findAllByAuctionId() throws Exception {
        when(bidRepository.findAll()).thenReturn(Arrays.asList(bid1));
        assertThat(bidService.findAllBidsByAuction(auction1))
                .hasSize(1);
    }


    @Test
    public void testSave() throws Exception {
        when(bidRepository.save(bid1)).thenReturn(bid1);
        assertThat(bidService.save(auction1 , bid1))
                .hasFieldOrPropertyWithValue("id",1L)
                .hasFieldOrPropertyWithValue("amount",BigDecimal.valueOf(50));
    }


    @Test
    public void testUpdate() throws Exception {
        when(auctionRepository.save(auction1)).thenReturn(auction1);
        assertThat(bidService.update(auction1, bid1, bid2))
                .hasFieldOrPropertyWithValue("id",1L);
    }

    @Test
    public void testDelete() throws Exception {
        bidService.delete(auction1, bid1);
        verify(auctionRepository, times(1)).save(any());
    }
}
