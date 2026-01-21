package com.example.demo.service;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.dto.StudentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface StudentService {
    StudentDTO createStudent(StudentDTO dto);

    StudentDTO getStudentById(Integer id);

    //List<StudentDTO> getAllStudents();
    Page<StudentDTO> getAllStudents(Pageable pageable);
    Page<StudentDTO> searchStudentsByName(String name, Pageable pageable);
    Page<StudentDTO> searchStudentsByEmail(String email, Pageable pageable);

    StudentDTO updateStudent(StudentDTO dto);

    void deleteStudent(Integer id);

    StudentDTO registerStudent(StudentDTO dto, String rawPassword);

    LoginResponse login(LoginRequest request);
}
