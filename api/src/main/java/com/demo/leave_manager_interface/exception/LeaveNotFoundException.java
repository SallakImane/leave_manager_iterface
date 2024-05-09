package com.demo.leave_manager_interface.exception;

public class LeaveNotFoundException extends Exception {
    public LeaveNotFoundException(String message) {
        super(message);
    }
}