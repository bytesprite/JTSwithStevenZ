package com.example.auctionapplication.auction;


import com.example.auctionapplication.domain.auction.Auction;
import com.example.auctionapplication.domain.auction.user.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class AuctionIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;


    @MockBean
    UserRepository userRepo;

    @Test
    @WithMockUser
    public void fullIntegrationTest() throws Exception {

        Auction auction = new Auction();
        auction.setDescription("testDescription");
        auction.setName("test");

        Auction incomingAuction = new Auction();
        incomingAuction.setName("putTest");
        incomingAuction.setDescription("testDescription");
        incomingAuction.setId(1L);

        this.mockMvc.perform(post("/auctions")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(auction)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("test")))
                .andExpect(jsonPath("$.description", is("testDescription")));

        this.mockMvc.perform(get("/auctions/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("test")))
                .andExpect(jsonPath("$.description", is("testDescription")));

        this.mockMvc.perform(put("/auctions/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(incomingAuction)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("putTest")))
                .andExpect(jsonPath("$.description", is("testDescription")));

        this.mockMvc.perform(delete("/auctions/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(isEmptyOrNullString()));

        this.mockMvc.perform(get("/auctions/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(isEmptyOrNullString()));
    }

}