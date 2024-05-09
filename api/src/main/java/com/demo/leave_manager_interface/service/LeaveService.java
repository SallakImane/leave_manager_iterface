package com.demo.leave_manager_interface.service;

import com.demo.leave_manager_interface.entity.Leave;
import com.demo.leave_manager_interface.exception.LeaveNotFoundException;
import com.demo.leave_manager_interface.repository.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeaveService {

    private final LeaveRepository leaveRepository;

    @Autowired
    public LeaveService(LeaveRepository leaveRepository) {
        this.leaveRepository = leaveRepository;
    }

    public List<Leave> getAllLeaves() {
        return leaveRepository.findAll();
    }

    public Optional<Leave> getLeaveById(Long id) {
        return leaveRepository.findById(id);
    }

    public Leave createLeave(Leave leave) {
        return leaveRepository.save(leave);
    }

    public Leave updateLeave(Long id, Leave leaveDetails) throws LeaveNotFoundException {
        Optional<Leave> optionalLeave = leaveRepository.findById(id);
        if (optionalLeave.isPresent()) {
            Leave leave = optionalLeave.get();
            leave.setMessage(leaveDetails.getMessage());
            leave.setStartDate(leaveDetails.getStartDate());
            leave.setEndDate(leaveDetails.getEndDate());
            leave.setType(leaveDetails.getType());
            return leaveRepository.save(leave);
        } else {
            throw new RuntimeException("Leave not found with id: " + id);
        }
    }

    public void deleteLeave(Long id) {
        leaveRepository.deleteById(id);
    }
}