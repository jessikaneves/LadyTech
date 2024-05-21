package com.elastech.LadyTech.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@RequestMapping("/authentication")
public class AuthenticationController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TechnicalRepository technicalRepository;
	@Autowired
	private AdministratorRepository administratorRepository;

	@PostMapping("/login")
	public String login(@RequestParam String userName, @RequestParam String password, HttpSession session,
			Model model) {
		User user = userRepository.findByUserName(userName);
		if (user != null && user.getPassword().equals(password)) {
			session.setAttribute("loggedUser", user);
			session.setAttribute("name", user.getName());
			return "redirect:/user/consult-called";
		}

		Technical technical = technicalRepository.findByUserName(userName);
		if (technical != null && technical.getPassword().equals(password)) {
			session.setAttribute("loggedUser", technical);
			session.setAttribute("technicalId", technical.getIdTechnical());
			session.setAttribute("name", technical.getName());
			return "redirect:/technical/consult-called";
		}

		Administrator administrator = administratorRepository.findByUserName(userName);
		if (administrator != null && administrator.getPassword().equals(password)) {
			session.setAttribute("loggedUser", administrator);
			session.setAttribute("name", administrator.getName());
			return "redirect:/called/consult-called";
		}

		return "redirect:/";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "index";
	}

}
