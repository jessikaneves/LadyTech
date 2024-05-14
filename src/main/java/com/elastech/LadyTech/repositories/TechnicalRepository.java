package com.elastech.LadyTech.repositories;

import com.elastech.LadyTech.models.Technical;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnicalRepository extends JpaRepository<Technical, Long> {
    boolean existsByUserName(String userName);

    Technical findByUserName(String username);
}
