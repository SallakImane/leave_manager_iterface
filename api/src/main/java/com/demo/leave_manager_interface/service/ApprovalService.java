package com.demo.leave_manager_interface.service;

import com.demo.leave_manager_interface.entity.Status;
import com.demo.leave_manager_interface.entity.User;
import com.demo.leave_manager_interface.enums.ApprovalStatus;
import com.demo.leave_manager_interface.enums.Role;
import com.demo.leave_manager_interface.exception.PermissionDeniedException;
import com.demo.leave_manager_interface.exception.StatusNotFoundException;
import com.demo.leave_manager_interface.exception.UserNotFoundException;
import com.demo.leave_manager_interface.repository.StatusRepository;
import com.demo.leave_manager_interface.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApprovalService {

    private final StatusRepository statusRepository;
    private final UserRepository userRepository;

    @Autowired
    public ApprovalService(StatusRepository statusRepository, UserRepository userRepository) {
        this.statusRepository = statusRepository;
        this.userRepository = userRepository;
    }

    // Method to approve a leave request
    public void approveLeaveRequest(Long requestId) throws StatusNotFoundException {
        Status status = statusRepository.findById(requestId)
                .orElseThrow(() -> new StatusNotFoundException("Leave request not found"));
        status.setApprovalStatus(ApprovalStatus.APPROVED);
        statusRepository.save(status);
    }


    // Method to reject a leave request
    public void rejectLeaveRequest(Long requestId) throws StatusNotFoundException {
        Status status = statusRepository.findById(requestId)
                .orElseThrow(() -> new StatusNotFoundException("Leave request not found"));
        status.setApprovalStatus(ApprovalStatus.REJECTED);
        statusRepository.save(status);
    }

    // Method to check if a user has permission to approve or reject a request
    public boolean hasPermissionToApproveOrReject(Long userId) throws PermissionDeniedException, UserNotFoundException {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Role userRole = user.getRole();

            // Check if the user has either the role of manager or admin
            if (userRole == Role.MANAGER || userRole == Role.ADMIN) {
                return true;
            } else {
                throw new PermissionDeniedException("User does not have permission to approve or reject requests");
            }
        } else {
            throw new UserNotFoundException("User with ID " + userId + " not found");
        }
    }
}
