package com.EmployeeManagement.EmployeeDetails.repository;


import com.EmployeeManagement.EmployeeDetails.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

    Optional<Employee> findByEmail(String email);

    List<Employee> findByDepartment(String department);

    boolean existsByEmail(String email);

    List<Employee> findByNameContainingIgnoreCase(String name);
}