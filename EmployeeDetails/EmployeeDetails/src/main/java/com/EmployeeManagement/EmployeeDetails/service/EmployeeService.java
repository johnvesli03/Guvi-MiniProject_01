package com.EmployeeManagement.EmployeeDetails.service;

import com.EmployeeManagement.EmployeeDetails.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> getAllEmployees();
    EmployeeDTO getEmployeeById(String id);
    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);
    EmployeeDTO updateEmployee(String id, EmployeeDTO employeeDTO);
    void deleteEmployee(String id);
}
