package com.training.ecommerce.repository;

import com.training.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
    User findByEmail(String Email);
}
