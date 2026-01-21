package com.example.demo.repo;

import com.example.demo.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    boolean existsByEmail(String email);

    Page<Student> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Student> findByEmailContainingIgnoreCase(String email, Pageable pageable);

    Page<Student> findByDeletedFalse(Pageable pageable);

    Optional<Student> findByIdAndDeletedFalse(Integer id);

    boolean existsByEmailAndDeletedFalse(String email);

    Page<Student> findByNameContainingIgnoreCaseAndDeletedFalse(String name, Pageable pageable);

    Optional<Student> findByEmailAndDeletedFalse(String email);

}
