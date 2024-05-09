package com.demo.leave_manager_interface.repository;

import com.demo.leave_manager_interface.entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRepository extends JpaRepository<Leave, Long> {
}
