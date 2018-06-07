package com.example.auctionapplication.domain.auction.event;

import com.example.auctionapplication.domain.auction.user.User;
import org.springframework.context.ApplicationEvent;

public class AuthUserAccountLockedEvent extends ApplicationEvent {

    public AuthUserAccountLockedEvent(User source) {
        super(source);
    }

    @Override
    public User getSource(){
        return (User)source;
    }
}
