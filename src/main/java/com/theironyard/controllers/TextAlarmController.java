package com.theironyard.controllers;

import com.stripe.exception.*;
import com.stripe.model.Charge;
import com.theironyard.entities.User;
import com.theironyard.exceptions.LoginFailedException;
import com.theironyard.exceptions.NotLoggedInException;
import com.theironyard.services.UserRepository;
import com.theironyard.utilities.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vasantia on 8/10/16.
 */

@Controller
public class TextAlarmController {

    public static final String SESSION_USER = "phone";

    @Autowired
    UserRepository userRepository;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public String home(Model model, HttpSession session){
        User user = userRepository.findFirstByPhone((Integer) session.getAttribute(SESSION_USER));
        if(user != null) {
            model.addAttribute("user", user);
        }
        return "home";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, Integer phone, String password) throws PasswordStorage.InvalidHashException,
            PasswordStorage.CannotPerformOperationException {
        User user = userRepository.findFirstByPhone(phone);

        if(user == null){
            user = new User(phone, PasswordStorage.createHash(password));
            userRepository.save(user);
        }

        if(!PasswordStorage.verifyPassword(password, user.getPassword())){
            throw new LoginFailedException();
        }

        session.setAttribute(SESSION_USER, phone);

        return "redirect:/";
    }

    @RequestMapping(path = "/pay", method = RequestMethod.POST)
    public String pay(HttpSession session, String stripeToken) throws CardException, APIException,
            AuthenticationException, InvalidRequestException, APIConnectionException {

        User user = userRepository.findFirstByPhone((Integer) session.getAttribute(SESSION_USER));

        if(user == null){
            throw new NotLoggedInException();
        }

        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", 2000);
        chargeParams.put("currency", "usd");
        chargeParams.put("source", stripeToken);
        chargeParams.put("description", "Wake up service fee");

        Charge charge = Charge.create(chargeParams);
        user.setChargeId(charge.getId());

        user.setPaid(true);
        userRepository.save(user);

        return "home";
    }

}
