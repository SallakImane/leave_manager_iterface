package com.demo.leave_manager_interface.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({
            PermissionDeniedException.class,
            StatusNotFoundException.class,
            UserNotFoundException.class,
            LeaveNotFoundException.class
    })
    public ResponseEntity<ErrorResponse> handleCustomException(Exception ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = "Internal Server Error";

        if (ex instanceof PermissionDeniedException) {
            status = HttpStatus.FORBIDDEN;
            message = ex.getMessage();
        } else if (ex instanceof StatusNotFoundException || ex instanceof UserNotFoundException || ex instanceof LeaveNotFoundException) {
            status = HttpStatus.NOT_FOUND;
            message = ex.getMessage();
        }

        ErrorResponse errorResponse = new ErrorResponse(status.value(), message);
        return new ResponseEntity<>(errorResponse, status);
    }
}
