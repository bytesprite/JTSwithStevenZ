package com.example.domain.auction.validation.exception.message;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Component;

import java.util.Locale;

@AllArgsConstructor
@Component
@Slf4j
public class SpringMvcErrorCodeInterpolator implements ApiErrorInterpolator {

    private MessageSource messageSource;

    @Override
    public String interpolate(DefaultMessageSourceResolvable e) {
        String message = messageSource.getMessage(e, Locale.getDefault());
        return message;
    }


}
