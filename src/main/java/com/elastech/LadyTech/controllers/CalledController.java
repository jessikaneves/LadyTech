package com.elastech.LadyTech.controllers;

import java.util.List;

import com.elastech.LadyTech.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.elastech.LadyTech.models.Called;
import com.elastech.LadyTech.models.User;
import com.elastech.LadyTech.repositories.CalledRepository;
import com.elastech.LadyTech.repositories.TechnicalRepository;
import org.springframework.web.bind.annotation.ModelAttribute;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/called")
public class CalledController {

	@Autowired
	private TechnicalRepository technicalRepository;

	@Autowired
	private CalledRepository calledRepository;

	@Autowired
	private UserRepository UserRepository;

	@Autowired
	private HttpSession session;

	/*
	@PostMapping("/create-called")
	public ResponseEntity<String> createCalled(@ModelAttribute Called called) {
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
	 */

	@PostMapping("/create-called")
	public String createTechnician(@ModelAttribute Called called, Model model) {
		called.setStatus("Aguardando Técnico");
		calledRepository.save(called);
		model.addAttribute("success", "Chamado cadastrado com sucesso!");
		return "redirect:/called/new-called";
	}
	
	@GetMapping("/consult-called")
	private String getAllCalled(Model model) {
		List<Called> called = calledRepository.findAll();
		List<User> users = UserRepository.findAll();
		model.addAttribute("chamados", called);
		model.addAttribute("users", users);
		return "administrador-tela-inicial";
	}
	@GetMapping("/byId/{idCalled}")
	private Called getCalledById(@PathVariable long idCalled) {
		return calledRepository.findById(idCalled).orElseThrow(() -> new RuntimeException("Chamado não encontrado"));
	}

	@GetMapping("/new-called")
	private String getNewCalled() {
		return "usuario-novo-chamado";
	}

	@PatchMapping("update/status/{idCalled}")
	private Called updateStatus(@PathVariable long idCalled, @RequestBody Called calledUpdate) {
		Called called = calledRepository.findById(idCalled)
				.orElseThrow(() -> new RuntimeException("Técnico não encontrado"));
		// set em cada um dos atributos autalizados com novo valor do tecnicoupdate
		called.setStatus(calledUpdate.getStatus());
		return calledRepository.save(called);
	}

	@PatchMapping("update/priority/{idCalled}")
	private Called updatePriority(@PathVariable long idCalled, @RequestBody Called calledUpdate) {
		Called called = calledRepository.findById(idCalled)
				.orElseThrow(() -> new RuntimeException("Técnico não encontrado"));
		// set em cada um dos atributos autalizados com novo valor do tecnicoupdate
		called.setPriority(calledUpdate.getPriority());
		return calledRepository.save(called);
	}

}
