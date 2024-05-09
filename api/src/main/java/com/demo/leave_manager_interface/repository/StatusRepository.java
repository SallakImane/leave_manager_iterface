package com.demo.leave_manager_interface.repository;


import com.demo.leave_manager_interface.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
}