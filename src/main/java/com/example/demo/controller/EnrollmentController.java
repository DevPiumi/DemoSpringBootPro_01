package com.example.demo.controller;


import com.example.demo.dto.EnrollmentDTO;
import com.example.demo.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<String> enroll(@RequestBody EnrollmentDTO dto) {
        enrollmentService.enrollStudent(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Student enrolled successfully");
    }

    @GetMapping("/student/{studentId}")
    public List<String> getCourses(@PathVariable Integer studentId) {
        return enrollmentService.getCoursesByStudent(studentId);
    }
}
