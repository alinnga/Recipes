package org.hyperskill.Recipe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.hyperskill.Recipe.model.User;
import org.hyperskill.Recipe.services.UserService;

import javax.validation.Valid;

@RestController
@Validated
public class RegistrationController {

    @Autowired
    UserService userService;

    @PostMapping("/api/register")
    public void register(@RequestBody @Valid User user){
        userService.register(user);
    }

    @GetMapping("/test")
    public String test(){
        return "OK";
    }

}
