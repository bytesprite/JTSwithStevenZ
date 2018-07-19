package com.example.domain.auction.security;

import com.example.domain.auction.event.AuthUserAccountLockedEvent;
import com.example.domain.auction.user.User;
import com.example.domain.auction.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class UserAccountLockFilter extends GenericFilterBean {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).get();

        if(user.getAccountlocked()){
            eventPublisher.publishEvent(new AuthUserAccountLockedEvent(user));
        }
    }
}
