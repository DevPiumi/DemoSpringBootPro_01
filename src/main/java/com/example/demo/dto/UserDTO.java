package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;


@Data //Support data handling
@NoArgsConstructor //create plain constructor without argument
@AllArgsConstructor //create plain constructor with argument
public class UserDTO {
    //create data transfer object

    private Integer id;

    @NotBlank(message = "Name is required")
    private String name;
}
