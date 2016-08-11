package com.theironyard.utilities;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.SmsFactory;
import com.twilio.sdk.resource.instance.Account;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by EddyJ on 8/11/16.
 */
public class TwilioAlarm {

    @Autowired
    Account twilioAccount;

    SmsFactory smsFactory;

    @PostConstruct
    public void setup(){
        smsFactory = twilioAccount.getSmsFactory();
    }

    @Scheduled(cron = " 0 7 * * * *")
    public void wakeUpAlarm() throws TwilioRestException {
        String phone = "7023434471";
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("From", "7026080979"));
        params.add(new BasicNameValuePair("To", phone));
        params.add(new BasicNameValuePair("Body", "Time to wake up!!"));
        System.out.println("Alarm sent");

        smsFactory.create(params);
    }
}
