package com.example.auctionapplication.auction;


import com.example.auctionapplication.domain.auction.Auction;
import com.example.auctionapplication.domain.auction.AuctionRepository;
import com.example.auctionapplication.domain.auction.AuctionServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;



@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("Duplicates")
public class AuctionServiceTests {

    @Mock
    AuctionRepository auctionRepository;

    @InjectMocks
    AuctionServiceImpl auctionService;

    @Test
    public void testFindAll(){
        Auction auction = new Auction();
        auction.setId(1L);

        when(auctionRepository.findAll()).thenReturn(Arrays.asList(auction));
        assertThat(auctionService.findAll()).hasSize(1);
    }



    @Test
    public void testFindByID(){
        Long id = 1L;


        Auction auction = new Auction();
        auction.setId(id);

        when(auctionRepository.findById(id)).thenReturn(Optional.of(auction));
        assertThat(auctionService.findByID(1L));

    }

    @Test
    public void testSave(){
        String name = "TEST";

        Auction auction = new Auction();
        auction.setName(name);

        when(auctionRepository.save(auction)).thenAnswer((Answer<Auction>) invocationOnMock -> {
            Auction savedAuction = new Auction();
            savedAuction.setId(1L);
            savedAuction.setName(((Auction)invocationOnMock.getArgument(0)).getName());
            return savedAuction;
        });
        assertThat(auctionService.save(auction)).hasFieldOrPropertyWithValue("id",1L)
                .hasFieldOrPropertyWithValue("name","TEST");
    }

    @Test
    public void testDeleteByID(){
        auctionService.deleteByID(999L);
        auctionService.deleteByID(999L);
        verify(auctionRepository, times(2)).deleteById(999L);
    }

    @Test
    public void testDelete(){
        auctionService.delete(new Auction());
        verify(auctionRepository, times(1)).delete(new Auction());
    }

//    @Test
//    public void integrationTestDeleteByID(){
//
//        String name = "Test";
//        Long id;
//        Auction auction = new Auction();
//        auction.setName(name);
//
//        //First we create our Auction & save it to the Repo
//        auctionService.save(auction);
//        id = auction.getId();
//
//        //Then we search to verify that the Auction is actually in the Repo
//        assertThat(auctionService.findByID(id)).isNotNull();
//
//        //Then we delete the Auction
//        auctionService.deleteByID(id);
//
//        //Then we search again and verify that the Auction is no longer in the Repo
//        assertThat(auctionService.findByID(id)).isNull();
//    }




    //Update
    //Delete
    //Read about @WebMVCTest (Test Controller)
    //Read up on Optional
    //Read up on Lambda
}