package com.example.demo.service.impl;


import com.example.demo.dto.CourseDTO;
import com.example.demo.model.Course;
import com.example.demo.repo.CourseRepository;
import com.example.demo.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

    @Override
    public CourseDTO createCourse(CourseDTO dto) {
        Course course = modelMapper.map(dto, Course.class);
        return modelMapper.map(courseRepository.save(course), CourseDTO.class);
    }

    @Override
    public CourseDTO getCourseById(Integer id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        return modelMapper.map(course, CourseDTO.class);
    }

    @Override
    public Page<CourseDTO> getAllCourses(Pageable pageable) {
        return courseRepository.findAll(pageable)
                .map(course -> modelMapper.map(course, CourseDTO.class));
    }

    @Override
    public Page<CourseDTO> searchCoursesByName(String name, Pageable pageable) {
        return courseRepository.findByCourseNameContainingIgnoreCase(name, pageable)
                .map(course -> modelMapper.map(course, CourseDTO.class));
    }


    @Override
    public CourseDTO updateCourse(CourseDTO dto) {
        courseRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Course course = modelMapper.map(dto, Course.class);
        return modelMapper.map(courseRepository.save(course), CourseDTO.class);
    }

    @Override
    public void deleteCourse(Integer id) {
        if (!courseRepository.existsById(id)) {
            throw new RuntimeException("Course not found");
        }
        courseRepository.deleteById(id);
    }
}
