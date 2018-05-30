package com.example.auctionapplication.domain.auction.user;

import com.example.auctionapplication.domain.AbstractEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
public class User extends AbstractEntity {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    private Integer loginAttempts;

    private Boolean accountLocked;
}


//http://www.baeldung.com/spring-security-authentication-with-a-database