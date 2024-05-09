package com.elastech.LadyTech.controllers;

import java.util.ArrayList;
import java.util.List;

import com.elastech.LadyTech.models.Technical;
import com.elastech.LadyTech.repositories.TechnicalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elastech.LadyTech.models.Client;
import com.elastech.LadyTech.repositories.AdministratorRepository;
import com.elastech.LadyTech.repositories.ClientRepository;


@RestController
@RequestMapping("/administrator")
public class AdministratorController{

	@Autowired
	private AdministratorRepository administratorRepository;

	@Autowired
	private TechnicalRepository technicalRepository;

	@Autowired
	private ClientRepository clientRepository;

	@PostMapping("/create-technician")
	public ResponseEntity<String> createTechnician(@RequestBody Technical technical) {

		if (technicalRepository.existsByUserName(technical.getUserName())) {
			return ResponseEntity.badRequest().body("Já existe um técnico cadastrado com esse usuário.");
		} else {

			technicalRepository.save(technical);

		}
		return ResponseEntity.ok("Técnico cadastrado com sucesso!");

	}

	@PostMapping("/create-client")
	public ResponseEntity<String> createClient(@RequestBody Client client) {

		if (clientRepository.existsByUserName(client.getUserName())) {
			return ResponseEntity.badRequest().body("Já existe um cliente cadastrado com esse usuário.");
		} else {

			clientRepository.save(client);

		}
		return ResponseEntity.ok("Cliente cadastrado com sucesso!");

	}

	@GetMapping("/consul-technician")
	public ResponseEntity<List<Technical>> consultTechnician() {
		List<Technical> technician = technicalRepository.findAll();
		return ResponseEntity.ok(technician);
	}

	@GetMapping("/consul-client")
	public ResponseEntity<List<Client>> consultClient() {
		List<Client> client = clientRepository.findAll();
		return ResponseEntity.ok(client);
	}

	@GetMapping("/consult-users")
	public ResponseEntity<List<Object>> consultUsers() {
		List<Object> allUsers = new ArrayList<>();

		List<Client> client = clientRepository.findAll();
		allUsers.addAll(client);

		List<Technical> technician = technicalRepository.findAll();
		allUsers.addAll(technician);

		return ResponseEntity.ok(allUsers);
	}
}
