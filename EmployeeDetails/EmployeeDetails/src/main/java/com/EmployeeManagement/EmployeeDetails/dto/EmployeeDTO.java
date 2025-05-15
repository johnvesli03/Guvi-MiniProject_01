package com.EmployeeManagement.EmployeeDetails.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDTO {

    private String id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Department is required")
    private String department;

    @NotNull(message = "Join date is required")
    @PastOrPresent(message = "Join date cannot be in the future")
    private LocalDate joinDate;

    @Size(max = 500, message = "Address cannot exceed 500 characters")
    private String address;

    @Pattern(
            regexp = "^[\\d\\s\\(\\)\\-\\+]*$",
            message = "Invalid phone number format"
    )
    private String phone;

    @NotNull(message = "Salary is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Salary cannot be negative")
    private BigDecimal salary;
}
