package com.example.demo.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@RestControllerAdvice //I will listen to exceptions thrown by any controller in this application.
public class GlobalExceptionHandler {

    // 404 - Resource Not Found
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(
            ResourceNotFoundException ex,
            HttpServletRequest request) {

        ErrorResponse error = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        String message = "Validation failed";

        if (!ex.getBindingResult().getFieldErrors().isEmpty()) {
            message = ex.getBindingResult()
                    .getFieldErrors()
                    .get(0)
                    .getDefaultMessage();
        }

        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                message,
                request.getRequestURI(),
                LocalDateTime.now()
        );

        return ResponseEntity.badRequest().body(error);
    }


//    // 500 - General Errors
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponse> handleAll(
//            Exception ex,
//            HttpServletRequest request) {
//
//        ErrorResponse error = new ErrorResponse(
//                HttpStatus.INTERNAL_SERVER_ERROR.value(),
//                "Internal server error",
//                request.getRequestURI(),
//                LocalDateTime.now()
//        );
//
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(error);
//    }

}
