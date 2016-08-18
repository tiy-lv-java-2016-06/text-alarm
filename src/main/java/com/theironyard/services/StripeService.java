package com.theironyard.services;

import com.stripe.Stripe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by EddyJ on 8/10/16.
 */
@Service
public class StripeService {

    @Value("${stripe.key.secret}")
    private String apiKey;

    @PostConstruct
    public void setup(){
        Stripe.apiKey = apiKey;
    }
}
