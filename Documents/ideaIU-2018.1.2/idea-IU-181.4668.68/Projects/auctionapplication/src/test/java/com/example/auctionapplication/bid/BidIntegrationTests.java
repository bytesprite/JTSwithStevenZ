package com.example.auctionapplication.bid;


import com.example.auctionapplication.domain.auction.Auction;
import com.example.auctionapplication.domain.auction.bid.Bid;
import com.example.auctionapplication.domain.auction.user.UserRepository;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.format.FormatterRegistry;
import org.springframework.test.context.junit4.SpringRunner;
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
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class BidIntegrationTests {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


    @MockBean
    UserRepository userRepo;


    private static Auction auction1 = new Auction();
    private static Auction auction2 = new Auction();
    private static Bid bid1 = new Bid();
    private static Bid bid2 = new Bid();

    @Before
    public void before(){
        auction1.setName("nameTest1");
        auction1.setDescription("descriptionTest1");

        auction2.setName("nameTest2");
        auction2.setDescription("descriptionTest2");

        bid1.setAuction(auction1);
        bid1.setAmount(BigDecimal.valueOf(25L));
        bid1.setId(1L);

        bid2.setAuction(auction1);
        bid2.setAmount(BigDecimal.valueOf(35L));
        bid2.setId(1L);
    }

    @Test
    @WithMockUser
    public void fullIntegrationTest() throws Exception{

        //save
        this.mockMvc.perform(post("/auctions/1/bids")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(bid1)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath(("$.amount"), is(bid1.getAmount().toBigInteger().intValueExact())));//int here

        //find by id
        this.mockMvc.perform(get("/auctions/1/bids/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));


        //update
        this.mockMvc.perform(put("/auctions/1/bids/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(bid2)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(bid2.getId().intValue())))
                .andExpect(jsonPath(("$.amount"), is(bid2.getAmount().intValueExact())));

        //find by auction
        this.mockMvc.perform(get("/auctions/1/bids")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(auction1)))
                .andDo(print())
                .andExpect(jsonPath("$[0].id", is(bid1.getId().intValue())))
                .andExpect(jsonPath("$[0].amount", is(bid1.getAmount().doubleValue())))//double here
                .andExpect(status().isOk());

        //delete
        this.mockMvc.perform((delete("/auctions/1/bids/1")))
                .andExpect(status().isNoContent())
                .andExpect(content().string(isEmptyOrNullString()));
    }


    @TestConfiguration
    static class InternalConfig {
        @Bean
        WebMvcConfigurer configurer() {
            return new WebMvcConfigurer() {
                @Override
                public void addFormatters(FormatterRegistry registry) {
                    registry.addConverter(String.class, Auction.class, id -> auction1);
                    registry.addConverter(String.class, Bid.class, id -> {
                        switch (id) {
                            case "1":
                                return bid1;
                            case "2":
                                return bid2;
                            default:
                                Bid bid = new Bid();
                                bid.setId(Long.parseLong(id));
                                return bid;
                        }
                    });
                }
            };
        }
    }

}

