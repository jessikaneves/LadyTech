package com.elastech.LadyTech.controller;

import com.elastech.LadyTech.models.Called;
import com.elastech.LadyTech.repositories.CalledRepository;
import com.elastech.LadyTech.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/client")
public class ClientController {
    @Autowired
    private ClientRepository userRepository;
    @Autowired
    private CalledRepository calledRepository;
    @GetMapping
    private List<Called> getAllCalled(){
        return calledRepository.findAll();
    }
    @GetMapping("{/id_call}")
    private Called getCalledById(@PathVariable long id_call){
        return calledRepository.findById(id_call)
                .orElseThrow(()->new RuntimeException("Chamado não encontrado"));
    }
    @PostMapping
    private Called saveCalled(@PathVariable long id_call, @RequestBody Called called){
        return calledRepository.save(called);
    }

}
