package com.example.demo.service.impl;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.dto.StudentDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Student;
import com.example.demo.repo.StudentRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    @Override
    public StudentDTO createStudent(StudentDTO dto) {
        if (studentRepository.existsByEmailAndDeletedFalse(dto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        Student student = modelMapper.map(dto, Student.class);
        return modelMapper.map(studentRepository.save(student), StudentDTO.class);
    }

    @Override
    public StudentDTO getStudentById(Integer id) {
        Student student = studentRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return modelMapper.map(student, StudentDTO.class);
    }

    @Override
    public Page<StudentDTO> getAllStudents(Pageable pageable) {
        return studentRepository.findByDeletedFalse(pageable)
                .map(student -> modelMapper.map(student, StudentDTO.class));
    }

    @Override
    public Page<StudentDTO> searchStudentsByName(String name, Pageable pageable) {
        return studentRepository.findByNameContainingIgnoreCaseAndDeletedFalse(name, pageable)
                .map(student -> modelMapper.map(student, StudentDTO.class));
    }

    @Override
    public Page<StudentDTO> searchStudentsByEmail(String email, Pageable pageable) {
        return studentRepository.findByEmailContainingIgnoreCase(email, pageable)
                .map(student -> modelMapper.map(student, StudentDTO.class));
    }

    @Override
    public StudentDTO updateStudent(StudentDTO dto) {
        studentRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Student student = modelMapper.map(dto, Student.class);
        return modelMapper.map(studentRepository.save(student), StudentDTO.class);
    }

    @Override
    public void deleteStudent(Integer id) {
        Student student = studentRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        student.setDeleted(true);
        studentRepository.save(student);
    }

    @Override
    public StudentDTO registerStudent(StudentDTO dto, String rawPassword) {

        if (studentRepository.existsByEmailAndDeletedFalse(dto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        Student student = new Student();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setPassword(passwordEncoder.encode(rawPassword));
        student.setDateOfBirth(dto.getDateOfBirth());
        student.setDeleted(false);

        Student saved = studentRepository.save(student);

        return new StudentDTO(
                saved.getId(),
                saved.getName(),
                saved.getEmail(),
                saved.getDateOfBirth()
        );
    }


    @Override
    public LoginResponse login(LoginRequest request) {

        Student student = studentRepository
                .findByEmailAndDeletedFalse(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), student.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(student.getEmail());

        return new LoginResponse(
                student.getId(),
                student.getName(),
                student.getEmail(),
                token
        );
    }
}
