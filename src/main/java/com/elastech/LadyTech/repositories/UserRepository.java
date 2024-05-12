package com.elastech.LadyTech.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elastech.LadyTech.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);

    boolean existsByUserName(String userName);
}
