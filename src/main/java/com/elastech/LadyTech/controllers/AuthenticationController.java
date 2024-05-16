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
	@Autowired
	private HttpSession session;
	private User usuarioLogado = new User();
	private Technical technicalLogado = new Technical();
	private Administrator administratorLogado = new Administrator();




	@PostMapping("/login")
	public String login(@RequestParam String username, @RequestParam String password, HttpSession session) {

		User user = userRepository.findByUserName(username);
		if (user != null && user.getPassword().equals(password)) {
			session.setAttribute("usuarioLogado", user);
			usuarioLogado.setName(user.getName());
			usuarioLogado.setIdUser(user.getIdUser());
			return "redirect:/user/consult-called";
		}

		Technical technical = technicalRepository.findByUserName(username);
		if (technical != null && technical.getPassword().equals(password)) {
			session.setAttribute("technicalLogado", technical);
			technicalLogado = technical;
			return "redirect:/technical/consult-called";
		}

		Administrator administrator = administratorRepository.findByUserName(username);
		if (administrator != null && administrator.getPassword().equals(password)) {
			session.setAttribute("administradorLogado", administrator);
			administratorLogado.setName(administrator.getName());
			administratorLogado.setIdAdministrator(administrator.getIdAdministrator());

			return "redirect:/called/consult-called";
		}

		return "redirect:/";
	}
	@GetMapping("/login")
	public Model usuarioHistorico(Model model, HttpSession session) {
		if(usuarioLogado.getName()!=null){
			model.addAttribute("nome",usuarioLogado.getName());

		}
		if(technicalLogado.getName()!=null){
			model.addAttribute("nome",technicalLogado.getName());

		}
		if(administratorLogado.getName()!=null){
			model.addAttribute("nome",administratorLogado.getName());

		}
		return model;
	}



	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/index.html";
	}
}
