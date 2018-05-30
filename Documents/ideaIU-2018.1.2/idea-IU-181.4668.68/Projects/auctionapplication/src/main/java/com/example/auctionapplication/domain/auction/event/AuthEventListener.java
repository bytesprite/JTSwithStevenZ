package com.example.auctionapplication.domain.auction.event;


import com.example.auctionapplication.domain.auction.user.User;
import com.example.auctionapplication.domain.auction.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

//@Lazy(false)
@Component
public class AuthEventListener {

    @Autowired
    private UserRepository userRepository;

    private Logger logger = LoggerFactory.getLogger(AuthEventListener.class);


    @EventListener
    public void onAuthFailureCredentialsEvent(AuthFailureCredentialsEvent event){
        Optional<User> userOptional =  userRepository.findByUsername(event.getSource().getUserPrincipal().getName());
        User user;

        logger.info("test");

        if(userOptional.isPresent()){
            user = userOptional.get();
            Integer loginAttempts = user.getLoginAttempts() + 1;

            if(loginAttempts <= 3){
                user.setLoginAttempts(loginAttempts + 1);
                logger.info("Authentication Failure in Credentials for Username {}. Number of Failed Attempts = {}", user.getUsername(), loginAttempts);
            }
            else{
                user.setAccountLocked(true);
                logger.info("Authentication Failure in Credentials for Username {}. User Account Now Locked", user.getUsername());
            }
        }
    }
}
