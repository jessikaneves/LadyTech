package com.elastech.LadyTech.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elastech.LadyTech.models.Administrator;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {

    Administrator findByUserName(String username);
}
