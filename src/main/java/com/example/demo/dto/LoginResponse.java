package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private Integer studentId;
    private String name;
    private String email;
    //private String message;
    private String token;
}
