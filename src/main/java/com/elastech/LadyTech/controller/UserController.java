package com.elastech.LadyTech.controller;

import com.elastech.LadyTech.models.UserModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    private UserRepository userRepository;

}
