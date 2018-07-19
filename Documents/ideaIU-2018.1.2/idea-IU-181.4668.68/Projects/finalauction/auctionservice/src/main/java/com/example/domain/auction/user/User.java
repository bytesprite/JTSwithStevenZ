package com.example.domain.auction.user;

import com.example.domain.AbstractEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

//@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class User extends AbstractEntity {


    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    private Integer loginattempts;

    private Boolean accountlocked;
}


//http://www.baeldung.com/spring-security-authentication-with-a-database