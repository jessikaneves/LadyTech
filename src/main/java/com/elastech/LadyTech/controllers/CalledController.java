package com.elastech.LadyTech.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.elastech.LadyTech.models.Called;
import com.elastech.LadyTech.models.Technical;
import com.elastech.LadyTech.models.User;
import com.elastech.LadyTech.repositories.CalledRepository;
import com.elastech.LadyTech.repositories.TechnicalRepository;
import com.elastech.LadyTech.repositories.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/called")
public class CalledController {

	@Autowired
	private CalledRepository calledRepository;

	@Autowired
	private UserRepository UserRepository;

	@Autowired
	private TechnicalRepository technicalRepository;

	@PostMapping("/create-called")
	public String createTechnician(@ModelAttribute Called called, Model model) {

		Long idUser = called.getUser().getIdUser();
		Optional<User> user = UserRepository.findById(idUser);

		if (user.isEmpty()) {
			model.addAttribute("error", "Usuário não encontrado.");
			return "error-page";
		}

		User userName = user.get();
		called.setUser(userName);
		called.setUserName(userName.getName());
		called.setDepartament(userName.getDepartament());
		called.setStatus("Aguardando Técnico");
		

		calledRepository.save(called);
		model.addAttribute("success", "Chamado cadastrado com sucesso!");

		return "redirect:/user/consult-called";

	}

	@GetMapping("/create-called")
	private String getNewCalled() {
		return "usuario-novo-chamado";
	}

	@GetMapping("/consult-called")
	private String getAllCalled(Model model) {
		List<Called> called = calledRepository.findAll();
		List<User> users = UserRepository.findAll();
		model.addAttribute("chamados", called);
		model.addAttribute("users", users);
		return "administrador-tela-inicial";
	}

	@PatchMapping("/update/status/{idCalled}")
	private String updateStatus(@PathVariable long idCalled, @RequestParam("status") String status,
			HttpSession session) {
		Called called = calledRepository.findById(idCalled)
				.orElseThrow(() -> new RuntimeException("Chamado não encontrado"));
		Long technicalId = (Long) session.getAttribute("technicalId");

		if (technicalId == null) {
			throw new RuntimeException("ID do técnico não encontrado na sessão");
		}

		Technical technical = technicalRepository.findById(technicalId)
				.orElseThrow(() -> new RuntimeException("Técnico não encontrado"));
		
		
		called.setTechnical(technical);
		called.setTechnicalName(technical.getName());
		called.setStatus(status);
		calledRepository.save(called);
		return "redirect:/technical/consult-called-open";
	}
	
	@PostMapping("/update/priority/{idCalled}")
	private String updatePriority(@PathVariable long idCalled, @RequestParam("priority") String priority) {
	    Called called = calledRepository.findById(idCalled)
	            .orElseThrow(() -> new RuntimeException("Chamado não encontrado"));
	    called.setPriority(priority);
	    calledRepository.save(called);
	    
	    return "redirect:/called/consult-called";
	}
}
