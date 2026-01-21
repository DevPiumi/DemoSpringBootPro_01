package com.example.demo.repo;

import com.example.demo.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {

    boolean existsByStudentIdAndCourseId(Integer studentId, Integer courseId);

    List<Enrollment> findByStudentId(Integer studentId);
}
