package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.management.relation.Role;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private LocalDate dateOfBirth;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private boolean deleted = false;   //SOFT DELETE FLAG: ✔ Default = active, Deleted students still exist in DB

    @Column(nullable = false)
    private String password;   // 🔐 encrypted password



}
