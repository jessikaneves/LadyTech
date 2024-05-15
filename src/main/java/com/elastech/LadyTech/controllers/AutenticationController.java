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
	public String login(@RequestParam String username, @RequestParam String password) {

		User user = userRepository.findByUserName(username);
		if (user != null && user.getPassword().equals(password)) {
			session.setAttribute("usuarioLogado", user);
			return "redirect:/user/consult-called";
		}

		Technical technical = technicalRepository.findByUserName(username);
		if (technical != null && technical.getPassword().equals(password)) {
			session.setAttribute("usuarioLogado", technical);
			return "redirect:/tecnico-historico";
		}

		Administrator administrator = administratorRepository.findByUserName(username);
		if (administrator != null && administrator.getPassword().equals(password)) {
			session.setAttribute("administradorLogado", administrator);

			return "redirect:/called/consult-called";
		}

		return "redirect:/login";
	}

	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/index.html";
	}
}
