package com.cagri.studentapi.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StudentRequest {
    @NotBlank(message = "Name cannot be blank")
    @Size(max = 255, message = "Name cannot exceed 255 characters")
    private String name;

    @NotBlank(message = "Surname cannot be blank")
    @Size(max = 255, message = "Surname cannot exceed 255 characters")
    private String surname;

    @NotBlank(message = "Student number cannot be blank")
    @Size(max = 20, message = "Student number cannot exceed 20 characters")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Student number must contain only alphanumeric characters")
    private String studentNumber;
}
