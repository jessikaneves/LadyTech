package com.elastech.LadyTech.repositories;

import com.elastech.LadyTech.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.JpaParameters;

public interface UserRepository extends JpaRepository<User, Long> {

}
