package com.EmployeeManagement.EmployeeDetails.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleResourceNotFoundException(ResourceNotFoundException ex, Model model, HttpServletRequest req) {
        if (req.getRequestURI().startsWith("/api/")) {
            return "forward:/api-error/resource-not-found";
        }
        model.addAttribute("errorMessage", ex.getMessage());
        return "error/404";
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public String handleDuplicateResourceException(DuplicateResourceException ex, Model model, HttpServletRequest req) {
        if (req.getRequestURI().startsWith("/api/")) {
            return "forward:/api-error/duplicate";
        }
        model.addAttribute("errorMessage", ex.getMessage());
        return "error/duplicate";
    }

    @ExceptionHandler(Exception.class)
    public String handleGenericException(Exception ex, Model model, HttpServletRequest req) {
        if (req.getRequestURI().startsWith("/api/")) {
            return "forward:/api-error/internal";
        }
        model.addAttribute("errorMessage", "Something went wrong: " + ex.getMessage());
        return "error/500";
    }

    @RequestMapping("/api-error/resource-not-found")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> apiResourceNotFound(HttpServletRequest request) {
        return buildJsonError("Resource not found", HttpStatus.NOT_FOUND);
    }

    @RequestMapping("/api-error/duplicate")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> apiDuplicate(HttpServletRequest request) {
        return buildJsonError("Duplicate resource", HttpStatus.CONFLICT);
    }

    @RequestMapping("/api-error/internal")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> apiInternalError(HttpServletRequest request) {
        return buildJsonError("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Map<String, Object>> buildJsonError(String message, HttpStatus status) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", System.currentTimeMillis());
        error.put("status", status.value());
        error.put("error", status.getReasonPhrase());
        error.put("message", message);
        return new ResponseEntity<>(error, status);
    }
}
