package com.elastech.LadyTech.controller;

import com.elastech.LadyTech.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/client")
public class ClientController {
    @Autowired
    private ClientRepository userRepository;

}
