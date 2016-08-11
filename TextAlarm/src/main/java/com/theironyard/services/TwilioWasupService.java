package com.theironyard.services;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.SmsFactory;
import com.twilio.sdk.resource.instance.Account;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nigel on 8/10/16.
 */
@Service
public class TwilioWasupService {

    SmsFactory smsFactory;

    @PostConstruct
    public void setUp(){
        smsFactory = twilioAccount.getSmsFactory();
    }
    @Autowired
    Account twilioAccount;

    @Scheduled(cron = "* 0 7 * * * ")
    public void wasup() throws TwilioRestException {

        String phone = "7024018279";
        List<NameValuePair> params = new ArrayList<>();

        params.add(new BasicNameValuePair("From", "7026231957"));
        params.add(new BasicNameValuePair("To", phone));
        params.add(new BasicNameValuePair("Body", "Wasup baby! Hope you have good day! BTW I'm sending this via Twilio! Wooo!"));
        System.out.println("Said wasup =)");

        smsFactory.create(params);
    }
}
