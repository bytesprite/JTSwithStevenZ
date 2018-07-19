package com.example.auditing.context;

import com.example.domain.auction.user.User;

public interface UserContextService {

    User getCurrentUser();
    Long getCurrentUserId();
    String getCurrentUsername();

}