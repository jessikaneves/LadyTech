package com.elastech.LadyTech.repositories;

import com.elastech.LadyTech.models.Called;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalledRepository extends JpaRepository<Called, Long> {
	List<Called> findByStatus(String status);

	List<Called> findByStatusNot(String status);
}
