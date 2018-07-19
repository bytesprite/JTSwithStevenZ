package com.example.domain.auction.event;


import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpServletRequest;

public class AuthFailureCredentialsEvent extends ApplicationEvent {

    public AuthFailureCredentialsEvent(HttpServletRequest source) {
        super(source);
    }

    @Override
    public HttpServletRequest getSource(){
        return (HttpServletRequest)source;
    }
}
