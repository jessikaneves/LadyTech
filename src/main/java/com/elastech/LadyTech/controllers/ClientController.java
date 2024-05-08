package com.elastech.LadyTech.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elastech.LadyTech.repositories.ClientRepository;

@RestController
@RequestMapping(value = "/user")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

}
