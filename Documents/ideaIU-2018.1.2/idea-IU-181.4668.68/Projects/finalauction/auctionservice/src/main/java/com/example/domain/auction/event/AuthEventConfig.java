package com.example.domain.auction.event;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthEventConfig {

    @Bean
    public AuthEventListener authEventListener() {
        return new AuthEventListener();
    }

}
