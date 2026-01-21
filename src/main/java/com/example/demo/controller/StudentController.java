package com.example.demo.controller;

import com.example.demo.dto.RegisterRequest;
import com.example.demo.dto.StudentDTO;
import com.example.demo.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springdoc.core.annotations.ParameterObject;


import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentDTO> create(@Valid @RequestBody StudentDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(studentService.createStudent(dto));
    }

    @GetMapping
    public Page<StudentDTO> getAll(
            @ParameterObject Pageable pageable) {

        return studentService.getAllStudents(pageable);
    }

    @GetMapping("/search")
    public Page<StudentDTO> searchStudents(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            Pageable pageable) {

        if (name != null) {
            return studentService.searchStudentsByName(name, pageable);
        }
        if (email != null) {
            return studentService.searchStudentsByEmail(email, pageable);
        }

        return studentService.getAllStudents(pageable);
    }

    @GetMapping("/{id}")
    public StudentDTO getById(@PathVariable Integer id) {
        return studentService.getStudentById(id);
    }

    @PutMapping
    public StudentDTO update(@Valid @RequestBody StudentDTO dto) {
        return studentService.updateStudent(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }



}
