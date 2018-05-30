package com.example.auctionapplication.bid;


import com.example.auctionapplication.domain.auction.Auction;
import com.example.auctionapplication.domain.auction.bid.Bid;
import com.example.auctionapplication.domain.auction.bid.BidController;
import com.example.auctionapplication.domain.auction.bid.BidServiceImpl;
import com.example.auctionapplication.domain.auction.security.WebSecurityConfig;
import com.example.auctionapplication.domain.auction.user.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings("Duplicates")
@RunWith(SpringRunner.class)
@WebMvcTest(BidController.class)
@Import(WebSecurityConfig.class)
public class BidControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BidServiceImpl mockBidService;

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
        bid1.setAmount(BigDecimal.valueOf(25));
        bid1.setId(1L);

        bid2.setAuction(auction1);
        bid2.setAmount(BigDecimal.valueOf(35));
        bid2.setId(2L);
    }

    @Test
    @WithMockUser
    public void testSave() throws Exception {
        when(mockBidService.save(auction1, bid1)).thenReturn(bid1);
        this.mockMvc.perform(post("/auctions/1/bids")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(bid1)))
                .andDo(print())
                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.auction", is(auction1)));
                .andExpect(jsonPath(("$.amount"), is(bid1.getAmount().toBigInteger().intValueExact())));
        verify(mockBidService, times(1)).save(auction1, bid1);
    }

    @Test
    @WithMockUser
    public void testUpdate() throws Exception{
        when(mockBidService.update(auction1, bid1, bid2)).thenReturn(bid2);
        this.mockMvc.perform(put("/auctions/1/bids/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(bid2)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(bid2.getId().intValue())))
                .andExpect(jsonPath(("$.amount"), is(bid2.getAmount().intValueExact())));
        verify(mockBidService, times(1)).update(any(), any(), any());
    }

    @Test
    @WithMockUser
    public void testFindAllBidsByAuction() throws Exception {

        when(mockBidService.findAllBidsByAuction(any())).thenReturn(Arrays.asList(bid1, bid2));
        this.mockMvc.perform(get("/auctions/1/bids")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(auction1)))
                .andDo(print())
                .andExpect(jsonPath("$[1].id", is(bid2.getId().intValue())))
                .andExpect(jsonPath("$[0].amount", is(bid1.getAmount().intValueExact())))
                .andExpect(status().isOk());
        verify(mockBidService, times(1)).findAllBidsByAuction(any());
    }

    @Test
    @WithMockUser
    public void testFindBidByID() throws Exception {

        when(mockBidService.findBidById(bid1.getId())).thenReturn(Optional.ofNullable(bid1));
        this.mockMvc.perform(get("/auctions/1/bids/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    @WithMockUser
    public void testDelete() throws Exception {

        this.mockMvc.perform(delete("/auctions/1/bids/1"))
                .andExpect(status().isNoContent())
                .andExpect(content().string(isEmptyOrNullString()));
        verify(mockBidService, times(1)).delete(any(), any());
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
