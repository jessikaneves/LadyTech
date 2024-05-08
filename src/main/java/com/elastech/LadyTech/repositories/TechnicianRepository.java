package com.elastech.LadyTech.repositories;

import com.elastech.LadyTech.models.Technician;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnicianRepository extends JpaRepository<Technician, Long> {
	boolean existsByUserName(String userName);
}
