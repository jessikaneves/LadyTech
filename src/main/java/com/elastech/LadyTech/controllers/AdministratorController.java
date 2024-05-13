package com.elastech.LadyTech.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.elastech.LadyTech.models.Administrator;
import com.elastech.LadyTech.models.Technical;
import com.elastech.LadyTech.models.User;
import com.elastech.LadyTech.repositories.AdministratorRepository;
import com.elastech.LadyTech.repositories.TechnicalRepository;
import com.elastech.LadyTech.repositories.UserRepository;

@Controller
@RequestMapping("/administrator")
public class AdministratorController {

	@Autowired
	private AdministratorRepository administratorRepository;

	@Autowired
	private TechnicalRepository technicalRepository;

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/create-technician")
	public ResponseEntity<String> createTechnician(@RequestBody Technical technical) {
		Long idAdministrator = technical.getAdministrator().getIdAdministrator();

		Optional<Administrator> admin = administratorRepository.findById(idAdministrator);

		if (admin.isEmpty()) {
			return ResponseEntity.badRequest().body("Administrador não encontrado.");
		}

		Administrator administrator = admin.get();
		technical.setAdministrator(administrator);

		if (technicalRepository.existsByUserName(technical.getUserName())) {

			return ResponseEntity.badRequest().body("Já existe um técnico cadastrado com esse usuário.");

		} else {
			technical.setAdministratorName(administrator.getName());
			technicalRepository.save(technical);
		}
		return ResponseEntity.ok("Técnico cadastrado com sucesso!");

	}

	@PostMapping("/create-user")
	public ResponseEntity<String> createUser(@RequestBody User user) {
		Long idAdministrator = user.getAdministrator().getIdAdministrator();

		Optional<Administrator> admin = administratorRepository.findById(idAdministrator);

		if (admin.isEmpty()) {
			return ResponseEntity.badRequest().body("Administrador não encontrado.");
		}

		Administrator administrator = admin.get();
		user.setAdministrator(administrator);

		if (userRepository.existsByUserName(user.getUserName())) {
			return ResponseEntity.badRequest().body("Já existe um cliente cadastrado com esse usuário.");
		} else {

			user.setAdministratorName(administrator.getName());
			userRepository.save(user);
		}

		return ResponseEntity.ok("Cliente cadastrado com sucesso!");
	}

	@GetMapping("/consult-users")
	public String consultUsers(Model model) {
		List<User> users = userRepository.findAll();
		List<Technical> technicians = technicalRepository.findAll();

		model.addAttribute("users", users);
		model.addAttribute("technicians", technicians);
		
		return "administrador-usuarios";
	}

	@PutMapping("/update-technician/{idTechnical}")
	public ResponseEntity<String> updateTechnicalId(@PathVariable Long idTechnical, @RequestBody Technical technical) {

		Optional<Technical> searchTechnical = technicalRepository.findById(idTechnical);

		if (searchTechnical.isEmpty()) {
			return ResponseEntity.badRequest().body("Técnico não encontrado");

		} else {

			Technical updateTechnical = searchTechnical.get();
			updateTechnical.setName(technical.getName());
			updateTechnical.setUserName(technical.getUserName());
			updateTechnical.setEmail(technical.getEmail());
			updateTechnical.setPassword(technical.getPassword());

			updateTechnical.setAdministrator(updateTechnical.getAdministrator());
			updateTechnical.setAdministratorName(updateTechnical.getAdministratorName());

			technicalRepository.save(updateTechnical);

		}

		return ResponseEntity.ok("Técnico atualizado com sucesso!");

	}

	@PutMapping("/update-user/{idUser}")
	public ResponseEntity<String> updateUserId(@PathVariable Long idUser, @RequestBody User user) {

		Optional<User> searchUser = userRepository.findById(idUser);

		if (searchUser.isEmpty()) {
			return ResponseEntity.badRequest().body("Usuário não encontrado.");
		} else {

			User updateUser = searchUser.get();
			updateUser.setName(user.getName());
			updateUser.setPhone(user.getPhone());
			updateUser.setUserName(user.getUserName());
			updateUser.setEmail(user.getEmail());
			updateUser.setPassword(user.getPassword());
			updateUser.setActive(user.isActive());

			updateUser.setAdministrator(updateUser.getAdministrator());
			updateUser.setAdministratorName(updateUser.getAdministratorName());
			updateUser.setRegisterDate(updateUser.getRegisterDate());

			userRepository.save(updateUser);

		}

		return ResponseEntity.ok("Usuário atualizado com sucesso!");

	}

	@DeleteMapping("/delete-technician/{idTechnical}")
	public ResponseEntity<String> deleteTechnicalId(@PathVariable Long idTechnical) {

		Optional<Technical> searchTechnical = technicalRepository.findById(idTechnical);

		if (searchTechnical.isEmpty()) {
			return ResponseEntity.badRequest().body("Técnico não encontrado.");
		} else {
			technicalRepository.deleteById(idTechnical);
			return ResponseEntity.ok("Técnico excluído com sucesso!");
		}
	}

	@DeleteMapping("/delete-user/{idUser}")
	public ResponseEntity<String> deleteClientId(@PathVariable Long idUser) {

		Optional<User> searchUser = userRepository.findById(idUser);

		if (searchUser.isEmpty()) {
			return ResponseEntity.badRequest().body("Usuário não encontrado.");
		} else {
			userRepository.deleteById(idUser);
			return ResponseEntity.ok("Usuário excluído com sucesso");
		}

	}
}