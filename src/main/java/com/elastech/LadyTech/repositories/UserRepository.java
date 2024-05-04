package com.elastech.LadyTech.repositories;

import com.elastech.LadyTech.models.UserModel;
import org.springframework.data.jpa.repository.query.JpaParameters;

public interface UserRepository extends JpaParameters<UserModel, Long> {

}
