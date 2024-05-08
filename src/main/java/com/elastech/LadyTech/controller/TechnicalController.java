package com.elastech.LadyTech.controller;

import com.elastech.LadyTech.models.Called;
import com.elastech.LadyTech.models.Technical;
import com.elastech.LadyTech.repositories.CalledRepository;
import com.elastech.LadyTech.repositories.TechnicalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class TechnicalController {
    @Autowired
    private CalledRepository calledRepository;
    @Autowired
    private TechnicalRepository tecnicoRepository;
    //visualizar todos os usuario com GET localhost:8080/tecnico
    @GetMapping
    private List<Technical>getaAllTechnicals(){
        return tecnicoRepository.findAll();
    }
    //visualizar apenas o usuario do id_tec com GET localhost:8080/tecnico/id_do_tecnico
    @GetMapping("/{id_tec}")
    private Technical getTechnicalById(@PathVariable long id_tec){
        return tecnicoRepository.findById(id_tec)
                // notação de erro caso o tecnico não seja encontado ele retorna essa mensagem
                .orElseThrow(()-> new RuntimeException("Técnico não encontrado"));
    }
    //criar usuario novo com POST localhost:8080 e body
    @PostMapping
    private Technical saveTechnical(@RequestBody Technical technical){
        return tecnicoRepository.save(technical);
    }
    //atualizar usuário existente com PUT
    @PutMapping("/{id_tec}")
    private Technical updateTechnical(@PathVariable long id_tec, @RequestBody Technical technicalupdate){
        Technical technical = tecnicoRepository.findById(id_tec)
                .orElseThrow(()-> new RuntimeException("Técnico não encontrado"));
        // set em cada um dos atributos autalizados com novo valor do tecnicoupdate
        technical.setName(technicalupdate.getName());
        technical.setEmail(technicalupdate.getEmail());
        technical.setPassword(technicalupdate.getPassword());
        technical.setDepartament(technicalupdate.getDepartament());
        return tecnicoRepository.save(technical);
    }
    //deletar usuario existente
    @DeleteMapping("/{id_tec}")
    private String deleteTechnical(@PathVariable long id_tec){
        Technical technical = tecnicoRepository.findById(id_tec)
                //antes de deletar verificar se usuario existe
                .orElseThrow(()-> new RuntimeException(("Tecnico não encontrado")));
        return "Técnico deletado com sucesso";
    }
    //Criar chamado
    @GetMapping
    private List<Called> getAllCalled(){
        return calledRepository.findAll();
    }
    @GetMapping("{/id_call}")
    private Called getCalledById(@PathVariable long id_call){
        return calledRepository.findById(id_call)
                .orElseThrow(()->new RuntimeException("Chamado não encontrado"));
    }
    @PutMapping("/{id_call}")
    private Called updateCalled(@PathVariable long id_call, @RequestBody Called calledupdate){
        Called called = calledRepository.findById(id_call)
                .orElseThrow(()-> new RuntimeException("Técnico não encontrado"));
        // set em cada um dos atributos autalizados com novo valor do tecnicoupdate
        called.setStatus(calledupdate.getStatus());
        return calledRepository.save(called);
    }
}
