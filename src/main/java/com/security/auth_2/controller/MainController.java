package com.security.auth_2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class MainController {

    @RequestMapping("/")
    public String home(){
        return "Welocome";
    }

    @RequestMapping("/user")
    public Principal principal(Principal user){
        return user;
    }

    @GetMapping("/login")
    public String welcome(Principal user){
        return "Login";
    }
}
