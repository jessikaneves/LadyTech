package com.elastech.LadyTech.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.elastech.LadyTech.models.Administrator;
import com.elastech.LadyTech.models.Technical;
import com.elastech.LadyTech.models.User;
import com.elastech.LadyTech.repositories.AdministratorRepository;
import com.elastech.LadyTech.repositories.TechnicalRepository;
import com.elastech.LadyTech.repositories.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/autentication")
public class AutenticationController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TechnicalRepository technicalRepository;
	@Autowired
	private AdministratorRepository administratorRepository;
	@Autowired
	private HttpSession session;

	@PostMapping("/login")
	private String login(@RequestParam String username, @RequestParam String password) {

		User user = userRepository.findByUserName(username);
		if (user != null) {
			session.setAttribute("usuarioLogade", user);
			return "redirect:/tela-usuario";
		}
		Technical technical = technicalRepository.findByUserName(username);
		if (technical != null) {
			session.setAttribute("usuarioLogado", technical);
			return "redirect:/tela-tecnico";
		}
		Administrator administrator = administratorRepository.findByUserName(username);
		if (administrator != null) {
			session.setAttribute("usuarioLogado", administrator);
			return "redirect:/cadastrouser";
		}
		return "redirect:/login";

	}
	
	@GetMapping("/logout")
	private String logout() {
	    session.invalidate(); 
	    return "redirect:/index.html"; 
	}
}
