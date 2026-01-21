package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
//import org.hibernate.annotations.AnyDiscriminatorImplicitValues;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Setter
@Getter
@Table(name = "users")
public class User {
    @Id //define primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //It means the database will automatically generate the primary key (ID) for each new record. You do NOT set the ID yourself.
    private Integer id;

    @Column(nullable = false)
    private String name;
}
