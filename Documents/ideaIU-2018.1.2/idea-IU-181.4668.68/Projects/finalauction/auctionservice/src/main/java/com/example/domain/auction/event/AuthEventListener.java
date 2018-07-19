package com.example.domain.auction.event;


import com.example.domain.auction.user.User;
import com.example.domain.auction.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthEventListener {

    @Autowired
    private UserRepository userRepository;

    private Logger logger = LoggerFactory.getLogger(AuthEventListener.class);


    @EventListener
    public void onAuthFailureCredentialsEvent(AuthenticationFailureBadCredentialsEvent event){

        Optional<User> userOptional =  userRepository.findByUsername(event.getAuthentication().getPrincipal().toString());
        User user;


        if(userOptional.isPresent()){
            user = userRepository.findByUsername(userOptional.get().getUsername()).get();
            Integer loginAttempts = user.getLoginattempts() + 1;

            if(loginAttempts <= 3){
                user.setLoginattempts(loginAttempts);
                logger.error("Authentication Failure in Credentials for User '{}'. Number of Failed Attempts = {}", user.getUsername(), loginAttempts);
            }
            else{
                user.setAccountlocked(true);
                logger.error("Authentication Failure in Credentials for User '{}'. User Account Now Locked", user.getUsername());
            }

            userRepository.save(user);
        }
    }

    //Need to somehow bypass the AuditConfig.

    @EventListener
    public void onAuthUserAccountLockedEvent(AuthUserAccountLockedEvent event){

        String username = event.getSource().getUsername();

        logger.error("Authentication Failure for User '{}'. User Account Is Already Locked!", username);

    }
}
