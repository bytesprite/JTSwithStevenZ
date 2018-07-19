package com.example.domain.auction.validation;


import com.example.domain.auction.Auction;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class AuctionValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Auction.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Auction auction = (Auction) o;
        if(auction.getName() == null){
            errors.rejectValue("name", "NotNull.Auction.name");
        }
        if(auction.getDescription() == null){
            errors.rejectValue("description","NotNull.Auction.description");
        }
    }
}
