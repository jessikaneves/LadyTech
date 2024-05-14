package com.elastech.LadyTech.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.elastech.LadyTech.models.Called;
import com.elastech.LadyTech.models.Technical;
import com.elastech.LadyTech.repositories.CalledRepository;
import com.elastech.LadyTech.repositories.TechnicalRepository;

@Controller
@RequestMapping("/called")
public class CalledController {

	@Autowired
	private TechnicalRepository technicalRepository;

	@Autowired
	private CalledRepository calledRepository;

	@GetMapping("/consult-called")
	private String getAllCalled(Model model) {
		List<Called> called = calledRepository.findAll();
		model.addAttribute("chamados", called);
		return "administrador-tela-inicial";
	}

	@PostMapping("/creat-called")
	public ResponseEntity<String> creatCalled(@RequestBody Called called) {
		Long idTechnical = called.getTechnical().getIdTechnical();

		Optional<Technical> technical = technicalRepository.findById(idTechnical);

		if (technical.isEmpty()) {
			return ResponseEntity.badRequest().body("Técnico não encontrado!");

		} else {
			String technicalName = technical.get().getName();

			called.setTechnicalName(technicalName);
			calledRepository.save(called);
			return ResponseEntity.ok("Chamado criado com sucesso!");

		}
	}

}
