package com.theironyard.utilities;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.SmsFactory;
import com.twilio.sdk.resource.instance.Account;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vasantia on 8/10/16.
 */

@Service
public class TwilioWakeUpText {

    @Autowired
    Account twilioAccount;

    SmsFactory smsFactory;

    @PostConstruct
    public void setup(){
        smsFactory = twilioAccount.getSmsFactory();
    }

    @Scheduled(cron = "0 0 7 * * *")
    public void wakeUp() throws TwilioRestException {
        String phone = "8087794018";
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("From", "7025087076"));
        params.add(new BasicNameValuePair("To", phone));
        params.add(new BasicNameValuePair("Body", "Are you awake???"));
        System.out.println("Sent Message");

        smsFactory.create(params);
    }
}
