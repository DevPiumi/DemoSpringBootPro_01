package com.example.demo.service;

import com.example.demo.dto.EnrollmentDTO;

import java.util.List;

public interface EnrollmentService {

    void enrollStudent(EnrollmentDTO dto);

    List<String> getCoursesByStudent(Integer studentId);
}
