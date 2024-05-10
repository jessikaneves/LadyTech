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

	// visualizar apenas um chamado
	@GetMapping("/{idCalled}")
	private Called getCalledById(@PathVariable long idCalled) {
		return calledRepository.findById(idCalled).orElseThrow(() -> new RuntimeException("Chamado não encontrado"));
	}

	// atualizar status
	@PatchMapping("/status/{idCalled}")
	private Called updateStatus(@PathVariable long idCalled, @RequestBody Called calledUpdate) {
		Called called = calledRepository.findById(idCalled)
				.orElseThrow(() -> new RuntimeException("Técnico não encontrado"));
		// set em cada um dos atributos autalizados com novo valor do tecnicoupdate
		called.setStatus(calledUpdate.getStatus());
		return calledRepository.save(called);
	}

	// atualizar prioridade
	@PatchMapping("/priority/{idCalled}")
	private Called updatePriority(@PathVariable long idCalled, @RequestBody Called calledUpdate) {
		Called called = calledRepository.findById(idCalled)
				.orElseThrow(() -> new RuntimeException("Técnico não encontrado"));
		// set em cada um dos atributos autalizados com novo valor do tecnicoupdate
		called.setPriority(calledUpdate.getPriority());
		return calledRepository.save(called);
	}
}
