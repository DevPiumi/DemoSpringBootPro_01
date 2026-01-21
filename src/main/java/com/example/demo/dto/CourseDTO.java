package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {
    private Integer id;

    @NotBlank(message = "Course name is required")
    private String courseName;

    private String description;

    private int credits;

}
