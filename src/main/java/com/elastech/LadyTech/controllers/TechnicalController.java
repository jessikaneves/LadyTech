package com.elastech.LadyTech.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elastech.LadyTech.models.Called;
import com.elastech.LadyTech.repositories.CalledRepository;

@RestController
@RequestMapping("/technical")
public class TechnicalController {
	
	@Autowired
	private CalledRepository calledRepository;

	// Criar chamado
	// visualizer todos os chamados cadastrados
	@GetMapping
	private List<Called> getAllCalled() {
		return calledRepository.findAll();
	}


}
