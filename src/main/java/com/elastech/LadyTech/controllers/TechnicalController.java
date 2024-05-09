package com.elastech.LadyTech.controller;

import com.elastech.LadyTech.models.Called;
import com.elastech.LadyTech.models.Technical;
import com.elastech.LadyTech.repositories.CalledRepository;
import com.elastech.LadyTech.repositories.TechnicalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/technical")
public class TechnicalController {
    @Autowired
    private CalledRepository calledRepository;
    @Autowired
    private TechnicalRepository tecnicoRepository;
    //Criar chamado
    //visualizer todos os chamados cadastrados
    @GetMapping
    private List<Called> getAllCalled(){
        return calledRepository.findAll();
    }
    //visualizar apenas um chamado
    @GetMapping("{/id_call}")
    private Called getCalledById(@PathVariable long id_call){
        return calledRepository.findById(id_call)
                .orElseThrow(()->new RuntimeException("Chamado não encontrado"));
    }
    //atualizar status
    @PatchMapping("/status/{id_call}")
    private Called updateStatus(@PathVariable long id_call, @RequestBody Called calledupdate){
        Called called = calledRepository.findById(id_call)
                .orElseThrow(()-> new RuntimeException("Técnico não encontrado"));
        // set em cada um dos atributos autalizados com novo valor do tecnicoupdate
        called.setStatus(calledupdate.getStatus());
        return calledRepository.save(called);
    }
    // atualizar prioridade
    @PatchMapping("/priority/{id_call}")
    private Called updatePriority(@PathVariable long id_call, @RequestBody Called calledupdate){
        Called called = calledRepository.findById(id_call)
                .orElseThrow(()-> new RuntimeException("Técnico não encontrado"));
        // set em cada um dos atributos autalizados com novo valor do tecnicoupdate
        called.setPriority(calledupdate.getPriority());
        return calledRepository.save(called);
    }
}
