package com.elastech.LadyTech.repositories;

import com.elastech.LadyTech.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
