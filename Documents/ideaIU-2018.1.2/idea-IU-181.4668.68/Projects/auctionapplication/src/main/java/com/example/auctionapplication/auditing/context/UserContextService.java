package com.example.auctionapplication.auditing.context;

import com.example.auctionapplication.domain.auction.user.User;

public interface UserContextService {

    User getCurrentUser();
    Long getCurrentUserId();
    String getCurrentUsername();

}