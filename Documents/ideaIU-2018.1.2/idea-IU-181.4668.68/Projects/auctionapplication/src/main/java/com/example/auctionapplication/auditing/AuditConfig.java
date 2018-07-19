package com.example.auctionapplication.auditing;

import com.example.auctionapplication.auditing.context.CurrentDateTimeService;
import com.example.auctionapplication.auditing.context.DateTimeService;
import com.example.auctionapplication.auditing.context.SpringSecurityUserContextService;
import com.example.auctionapplication.auditing.context.UserContextService;
import com.example.auctionapplication.domain.auction.security.UnauthenticatedException;
import com.example.auctionapplication.domain.auction.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "userContextProvider", dateTimeProviderRef = "dateTimeProvider")
public class AuditConfig {

    @Bean
    UserContextService userContextService(UserRepository userRepository){
        return new SpringSecurityUserContextService(userRepository);
    }

    @Bean
    DateTimeService dateTimeService(){
        return new CurrentDateTimeService();
    }

    @Bean
    DateTimeProvider dateTimeProvider(DateTimeService dateTimeService){
        return () -> Optional.of(dateTimeService.getCurrentDateTime());
    }

    @Bean
    AuditorAware<String> userContextProvider(UserContextService userContextService){
        return () -> {
            try{
                return Optional.of(userContextService.getCurrentUsername());
            }
            catch(UnauthenticatedException e){
                return Optional.of("System");
            }
        };
    }
}