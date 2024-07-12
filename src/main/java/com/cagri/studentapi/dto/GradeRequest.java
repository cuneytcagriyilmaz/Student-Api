package com.cagri.studentapi.dto;


import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class GradeRequest {
    @NotBlank(message = "Code cannot be blank")
    @Size(max = 50, message = "Code cannot exceed 50 characters")
    private String code;

    @NotNull(message = "Value cannot be null")
    private int value;

    @Min(value = 0, message = "Value must be at least 0")
    @Max(value = 100, message = "Value must be at most 100")
    private String studentNumber;

}
