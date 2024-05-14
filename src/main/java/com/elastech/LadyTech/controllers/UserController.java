package com.elastech.LadyTech.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elastech.LadyTech.models.Called;
import com.elastech.LadyTech.repositories.CalledRepository;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private CalledRepository calledRepository;

	@GetMapping
	private List<Called> getAllCalled() {
		return calledRepository.findAll();
	}

	@GetMapping("/{idCalled}")
	private Called getCalledById(@PathVariable long idCalled) {
		return calledRepository.findById(idCalled).orElseThrow(() -> new RuntimeException("Chamado não encontrado"));
	}

	@PostMapping
	private Called saveCalled(@PathVariable long idCalled, @RequestBody Called called) {
		return calledRepository.save(called);
	}

}



