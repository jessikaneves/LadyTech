package com.elastech.LadyTech.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elastech.LadyTech.models.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
	boolean existsByUserName(String userName);
}
