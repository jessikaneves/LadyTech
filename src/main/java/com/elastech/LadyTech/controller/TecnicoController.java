package com.elastech.LadyTech.controller;

import com.elastech.LadyTech.models.Tecnico;
import com.elastech.LadyTech.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tecnico")
public class TecnicoController {
    @Autowired
    private TecnicoRepository tecnicoRepository;
    //visualizar todos os usuario com GET localhost:8080/tecnico
    @GetMapping
    private List<Tecnico>getAllTecnicos(){
        return tecnicoRepository.findAll();
    }
    //visualizar apenas o usuario do id_tec com GET localhost:8080/tecnico/id_do_tecnico
    @GetMapping("/{id_tec}")
    private Tecnico getTecnicoById(@PathVariable long id_tec){
        return tecnicoRepository.findById(id_tec)
                // notação de erro caso o tecnico não seja encontado ele retorna essa mensagem
                .orElseThrow(()-> new RuntimeException("Tecnico not found with "+id_tec));
    }
    //criar usuario novo com POST localhost:8080 e body
    @PostMapping
    private Tecnico saveTecnico(@RequestBody Tecnico tecnico){
        return tecnicoRepository.save(tecnico);
    }
    //atualizar usuário existente com PUT
    @PutMapping("/{id_tec}")
    private Tecnico updateTecnico(@PathVariable long id_tec, @RequestBody Tecnico tecnicoupdate){
        Tecnico tecnico = tecnicoRepository.findById(id_tec)
                .orElseThrow(()-> new RuntimeException("Tecnico not found with id "+ id_tec));
        // set em cada um dos atributos autalizados com novo valor do tecnicoupdate
        tecnico.setName(tecnicoupdate.getName());
        tecnico.setEmail(tecnicoupdate.getEmail());
        tecnico.setPassword(tecnicoupdate.getPassword());
        tecnico.setDepartament(tecnicoupdate.getDepartament());
        return tecnicoRepository.save(tecnico);
    }
    //deletar usuario existente
    @DeleteMapping("{/id_tec}")
    private String deleteTecnico(@PathVariable long id_tec){
        Tecnico tecnico = tecnicoRepository.findById(id_tec)
                //antes de deletar verificar se usuario existe
                .orElseThrow(()-> new RuntimeException(("Tecnico not found with id "+id_tec)));
        return "Tecnico deleted successfuly with id "+id_tec;
    }
}
