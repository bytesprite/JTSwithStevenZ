package com.example.domain.auction.security;

import com.example.domain.auction.user.User;
import com.example.domain.auction.user.UserPrincipal;
import com.example.domain.auction.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        if(!user.isPresent()){
            throw new UsernameNotFoundException(username);
        }
        return new UserPrincipal(user.get());
    }
}

//http -v -a user: POST localhost:8080/auctions name="testAuction" description="test"