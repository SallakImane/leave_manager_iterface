package com.demo.leave_manager_interface.controller;

import com.demo.leave_manager_interface.exception.StatusNotFoundException;
import com.demo.leave_manager_interface.service.ApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/approval")
public class ApprovalController {

    private final ApprovalService approvalService;

    @Autowired
    public ApprovalController(ApprovalService approvalService) {
        this.approvalService = approvalService;
    }

    @PutMapping("/approve/{requestId}")
    public ResponseEntity<String> approveLeaveRequest(@PathVariable Long requestId){
        try {
            approvalService.approveLeaveRequest(requestId);
            return ResponseEntity.ok("Leave request approved successfully.");
        } catch (StatusNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/reject/{requestId}")
    public ResponseEntity<String> rejectLeaveRequest(@PathVariable Long requestId) {
        try {
            approvalService.rejectLeaveRequest(requestId);
            return ResponseEntity.ok("Leave request rejected successfully.");
        } catch (StatusNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}