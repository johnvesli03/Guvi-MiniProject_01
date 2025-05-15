package com.EmployeeManagement.EmployeeDetails.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "employees")
public class Employee {

    @Id
    private String id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Indexed(unique = true)
    private String email;

    @NotBlank(message = "Department is required")
    private String department;

    @NotNull(message = "Join date is required")
    @PastOrPresent(message = "Join date cannot be in the future")
    private LocalDate joinDate;

    @Size(max = 500, message = "Address cannot exceed 500 characters")
    private String address;

    @Pattern(regexp = "^[\\d\\s\\(\\)\\-\\+]*$", message = "Invalid phone number format")
    private String phone;

    @DecimalMin(value = "0.0", inclusive = true, message = "Salary cannot be negative")
    private BigDecimal salary;
}
