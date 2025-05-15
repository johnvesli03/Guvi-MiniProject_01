package com.EmployeeManagement.EmployeeDetails.controller;

import com.EmployeeManagement.EmployeeDetails.dto.EmployeeDTO;
import com.EmployeeManagement.EmployeeDetails.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeViewController {

    private final EmployeeService employeeService;

    // 1. List all employees
    @GetMapping
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "employees/list"; // templates/employees/list.html
    }

    // 2. Show form to create new employee
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("employee", new EmployeeDTO());
        return "employees/create"; // templates/employees/create.html
    }

    // 3. Handle form submission to create employee
    @PostMapping("/create")
    public String createEmployee(@ModelAttribute("employee") EmployeeDTO employeeDTO) {
        employeeService.createEmployee(employeeDTO);
        return "redirect:/employees";
    }

    // 4. Show form to edit an existing employee
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        EmployeeDTO employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "employees/edit"; // templates/employees/edit.html
    }

    // 5. Handle form submission to update employee
    @PostMapping("/{id}/edit")
    public String updateEmployee(@PathVariable String id, @ModelAttribute("employee") EmployeeDTO employeeDTO) {
        employeeService.updateEmployee(id, employeeDTO);
        return "redirect:/employees"; // Redirect after successful update
    }

    // 6. View employee details (No form submission, just display)
    @GetMapping("/{id}")
    public String viewEmployeeDetails(@PathVariable String id, Model model) {
        EmployeeDTO employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "employees/view"; // templates/employees/view.html
    }

    // 7. Delete employee (use POST method via form submission)
    @PostMapping("/{id}/delete")
    public String deleteEmployee(@PathVariable String id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees"; // Redirect after successful deletion
    }
}
