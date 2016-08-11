package com.theironyard.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import com.theironyard.objects.User;
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
 * Created by Nigel on 8/10/16.
 */
@Controller
@RequestMapping("/")
public class TextAlarmController {

    public static final String SESSION_USERNAME = "username";

    private User theUser;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public String homie(Model model, HttpSession session){
        model.addAttribute("user", theUser);

        return "home";
    }

    @RequestMapping(path = "/login")
    public String login(HttpSession session, String username, String password) throws PasswordStorage.CannotPerformOperationException {
        theUser = new User(username, PasswordStorage.createHash(password));

        session.setAttribute(SESSION_USERNAME, username);

        return "redirect:/";

    }

    @RequestMapping(path = "/pay", method = RequestMethod.POST)
    public String pay(HttpSession session, String stripeToken) throws CardException, APIException, AuthenticationException, InvalidRequestException, APIConnectionException {

        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", 2000);
        chargeParams.put("currency", "usd");
        chargeParams.put("source", stripeToken);
        chargeParams.put("description", "charge for the privilege of using a site built by ChocoDonis!");

        Charge charge;
        charge = Charge.create(chargeParams);

        theUser.setPurchaseId(charge.getId());

        return "home";
    }
}
