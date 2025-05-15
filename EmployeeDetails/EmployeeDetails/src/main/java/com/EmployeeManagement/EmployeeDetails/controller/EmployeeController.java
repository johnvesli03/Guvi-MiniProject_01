package com.EmployeeManagement.EmployeeDetails.controller;

import com.EmployeeManagement.EmployeeDetails.dto.EmployeeDTO;
import com.EmployeeManagement.EmployeeDetails.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@SecurityRequirement(name = "basicAuth")
@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
@Tag(name = "Employee API", description = "API for CRUD operations on employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    // 1. Get all employees
    @GetMapping
    @Operation(summary = "Get all employees", description = "Fetches the list of all employees")
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // 2. Create a new employee
    @PostMapping
    @Operation(summary = "Create a new employee", description = "Creates a new employee in the system")
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO createdEmployee = employeeService.createEmployee(employeeDTO);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    // 3. Get a single employee by ID
    @GetMapping("/{id}")
    @Operation(summary = "Get an employee by ID", description = "Fetches details of a specific employee by their ID")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable String id) {
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
    }

    // 4. Update an existing employee
    @PutMapping("/{id}")
    @Operation(summary = "Update an employee", description = "Updates the details of an existing employee")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable String id, @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO updatedEmployee = employeeService.updateEmployee(id, employeeDTO);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    // 5. Delete an employee
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an employee", description = "Deletes an employee by their ID")
    public ResponseEntity<Void> deleteEmployee(@PathVariable String id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
