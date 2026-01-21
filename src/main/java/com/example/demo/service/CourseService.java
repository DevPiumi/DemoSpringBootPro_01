package com.example.demo.service;

import com.example.demo.dto.CourseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseService {
    CourseDTO createCourse(CourseDTO dto);

    CourseDTO getCourseById(Integer id);

    Page<CourseDTO> getAllCourses(Pageable pageable);
    Page<CourseDTO> searchCoursesByName(String name, Pageable pageable);


    CourseDTO updateCourse(CourseDTO dto);

    void deleteCourse(Integer id);
}
