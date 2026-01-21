package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.dto.StudentDTO;
import com.example.demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final StudentService studentService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return studentService.login(request);
    }
    @PostMapping("/register")
    public StudentDTO register(@RequestBody RegisterRequest request) {

        StudentDTO dto = new StudentDTO(
                null,
                request.getName(),
                request.getEmail(),
                request.getDateOfBirth()
        );

        return studentService.registerStudent(dto, request.getPassword());
    }



}
