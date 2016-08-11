package com.theironyard.configs;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.instance.Account;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by EddyJ on 8/10/16.
 */
@Configuration
@EnableScheduling
public class AppConfig {
    @Bean
    public TwilioRestClient twilioRestClient(@Value("${twilio.sid}") String sid, @Value("${twilio.auth}") String auth){
        return new TwilioRestClient(sid, auth);
    }
    @Bean
    public Account twilioAccount(TwilioRestClient twilioRestClient){
        return twilioRestClient.getAccount();
    }
}
