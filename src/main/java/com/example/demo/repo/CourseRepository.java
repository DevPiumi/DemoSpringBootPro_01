package com.example.demo.repo;

import com.example.demo.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    Page<Course> findByCourseNameContainingIgnoreCase(String courseName, Pageable pageable);
}
