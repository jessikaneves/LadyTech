package com.elastech.LadyTech.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.elastech.LadyTech.models.Called;
import com.elastech.LadyTech.repositories.CalledRepository;

@Controller
@RequestMapping("/technical")
public class TechnicalController {

	@Autowired
	private CalledRepository calledRepository;

	@GetMapping
	private List<Called> getAllCalled() {
		return calledRepository.findAll();
	}

	@GetMapping("/consult-called")
	private String getAllCalled(Model model) {
		List<Called> called = calledRepository.findAll();
		model.addAttribute("chamados", called);
		return "tecnico-historico";
	}

	@GetMapping("/consult-called-open")
	private String getCalledOpen(Model model) {
		String status = "Finalizado";

		List<Called> called = calledRepository.findByStatusNot(status);

		model.addAttribute("chamados", called);
		return "tecnico-chamados-abertos";
	}

}
