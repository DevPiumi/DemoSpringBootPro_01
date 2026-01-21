package com.example.demo.service.impl;

import com.example.demo.dto.EnrollmentDTO;
import com.example.demo.model.Course;
import com.example.demo.model.Enrollment;
import com.example.demo.model.Student;
import com.example.demo.repo.CourseRepository;
import com.example.demo.repo.EnrollmentRepository;
import com.example.demo.repo.StudentRepository;
import com.example.demo.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Override
    public void enrollStudent(EnrollmentDTO dto){
        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        if (enrollmentRepository.existsByStudentIdAndCourseId(
                dto.getStudentId(), dto.getCourseId())) {
            throw new RuntimeException("Student already enrolled in this course");
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);

        enrollmentRepository.save(enrollment);
    }

    @Override
    public List<String> getCoursesByStudent(Integer studentId) {

        if (!studentRepository.existsById(studentId)) {
            throw new RuntimeException("Student not found");
        }

        return enrollmentRepository.findByStudentId(studentId)
                .stream()
                .map(e -> e.getCourse().getCourseName())
                .toList();
    }

}
