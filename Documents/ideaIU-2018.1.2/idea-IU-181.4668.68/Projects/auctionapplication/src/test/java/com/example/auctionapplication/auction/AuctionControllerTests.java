package com.example.auctionapplication.auction;

import com.example.auctionapplication.domain.auction.Auction;
import com.example.auctionapplication.domain.auction.AuctionController;
import com.example.auctionapplication.domain.auction.AuctionServiceImpl;
import com.example.auctionapplication.domain.auction.security.WebSecurityConfig;
import com.example.auctionapplication.domain.auction.user.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.security

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SuppressWarnings("Duplicates")
@RunWith(SpringRunner.class)
@WebMvcTest(AuctionController.class)
@Import(WebSecurityConfig.class)
public class AuctionControllerTests{

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    AuctionServiceImpl mockAuctionService;

    @MockBean
    UserRepository userRepo;


    @Test
    @WithMockUser
    public void testSave() throws Exception{

        Auction auction =  new Auction();

        auction.setName("test");
        auction.setDescription("testDescription");
        auction.setId(1L);

        when(mockAuctionService.save(auction)).thenReturn(auction);
        this.mockMvc.perform(post("/auctions")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(auction)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("test")))
                .andExpect(jsonPath("$.description", is("testDescription")));
        verify(mockAuctionService, times(1)).save(auction);
    }

    @Test
    @WithMockUser
    public void createNoNameAuction() throws Exception{
        Auction auction = new Auction();
        auction.setId(1L);

        when(mockAuctionService.save(auction)).thenReturn(auction);
        this.mockMvc.perform(post("/auctions")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(auction)))
                .andDo(print())
                .andExpect(status().isBadRequest());
//                .andExpect(jsonPath("$.error", is("Bad Result")));
    }

    @Test
    @WithMockUser
    public void testUpdate() throws Exception{
        Long id = 1L;

        Auction auction = new Auction();
        auction.setName("test");
        auction.setDescription("testDescription");
        auction.setId(id);

        when(mockAuctionService.update(id, auction)).thenReturn(auction);
        this.mockMvc.perform(put("/auctions/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(auction)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("test")))
                .andExpect(jsonPath("$.description", is("testDescription")));
    }

    @Test
    @WithMockUser
    public void testFindAll() throws Exception {

        Auction auction = new Auction();

        auction.setName("test");
        auction.setDescription("testDescription");
        auction.setId(1L);

        when(mockAuctionService.findAll()).thenReturn(Arrays.asList(auction, new Auction()));
        this.mockMvc.perform(get("/auctions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("test")));
    }

    @Test
    @WithMockUser
    public void testFindByID() throws Exception{

        Auction auction = new Auction();

        auction.setName("test");
        auction.setDescription("testDescription");
        auction.setId(1L);

        when(mockAuctionService.findByID(1L)).thenReturn(Optional.ofNullable(auction));
        this.mockMvc.perform(get("/auctions/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("test")))
                .andExpect(jsonPath("$.description", is("testDescription")));
        verify(mockAuctionService, times(1)).findByID(1L);
        verify(mockAuctionService, times(0)).findByID(2L);
    }

    @Test
    @WithMockUser
    public void testDeleteAuctionsById() throws Exception{
        this.mockMvc.perform(delete("/auctions/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(isEmptyOrNullString()));
        verify(mockAuctionService, times(1)).deleteByID(1L);
    }

    @Test
    @WithMockUser
    public void testDeleteAuctions() throws Exception{
        Auction auction = new Auction();
        auction.setId(3L);
        auction.setName("test");

        this.mockMvc.perform(delete("/auctions")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"id\":3,\"name\":\"test\"}"))
                .andExpect(content().string(isEmptyOrNullString()));
        verify(mockAuctionService, times(1)).delete(auction);
    }
}
