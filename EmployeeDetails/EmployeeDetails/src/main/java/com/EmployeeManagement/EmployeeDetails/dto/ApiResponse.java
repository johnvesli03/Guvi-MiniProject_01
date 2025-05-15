package com.EmployeeManagement.EmployeeDetails.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    private String message;
    private boolean success;
    private LocalDateTime timestamp = LocalDateTime.now();

    public ApiResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}
