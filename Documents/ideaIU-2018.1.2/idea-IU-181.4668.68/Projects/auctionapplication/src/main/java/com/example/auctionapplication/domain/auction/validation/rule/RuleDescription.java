package com.example.auctionapplication.domain.auction.validation.rule;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RuleDescription<T>{
    private Rule<T> rule;
    private Boolean continueOnError;
}
