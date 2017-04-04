package org.FuelPoints.controllers;

import org.FuelPoints.entities.User;
import org.FuelPoints.services.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class FuelPointsController {
    @Autowired
    UserRepository users;


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home() {

        return "home";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String userName, String password) throws Exception {
        User user = users.findFirstByName(userName);
        if (user == null) {
            users.save(new User(userName, password));
        } else if (!user.verifyPassword(password)) {
            throw new Exception("Login failed");
        }
        session.setAttribute("userName", userName);
        return "redirect:/";
    }
}

