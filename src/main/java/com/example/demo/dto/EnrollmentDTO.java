package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentDTO {
    @ManyToOne
    @JsonIgnore
    private Integer studentId;

    @ManyToOne
    @JsonIgnore
    private Integer courseId;

}
