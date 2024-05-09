package com.demo.leave_manager_interface.repository;

import com.demo.leave_manager_interface.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);

}
