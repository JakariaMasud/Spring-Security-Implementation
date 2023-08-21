package com.example.springSecurityImplementation.respositories;

import com.example.springSecurityImplementation.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<User,Integer> {
    Optional<User> findByName(String name);
}
