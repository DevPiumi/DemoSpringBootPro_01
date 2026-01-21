package com.example.demo.controller;

import com.example.demo.dto.CourseDTO;
import com.example.demo.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<CourseDTO> create(@Valid @RequestBody CourseDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(courseService.createCourse(dto));
    }

    @GetMapping
    public Page<CourseDTO> getAll(@ParameterObject Pageable pageable) {
        return courseService.getAllCourses(pageable);
    }

    @GetMapping("/{id}")
    public CourseDTO getById(@PathVariable Integer id) {
        return courseService.getCourseById(id);
    }

    @GetMapping("/search")
    public Page<CourseDTO> searchCourses(
            @RequestParam String name,
            Pageable pageable) {

        return courseService.searchCoursesByName(name, pageable);
    }


    @PutMapping
    public CourseDTO update(@Valid @RequestBody CourseDTO dto) {
        return courseService.updateCourse(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
