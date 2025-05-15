document.addEventListener('DOMContentLoaded', function() {
    'use strict';

    // ==================== Form Validation ====================
    const forms = document.querySelectorAll('.needs-validation');

    Array.prototype.slice.call(forms).forEach(function(form) {
        form.addEventListener('submit', function(event) {
            if (!form.checkValidity()) {
                event.preventDefault();
                event.stopPropagation();
            }
            form.classList.add('was-validated');
        }, false);
    });

    // Email Validation
    const emailInput = document.getElementById('email');
    if (emailInput) {
        emailInput.addEventListener('blur', function() {
            const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
            if (this.value && !emailRegex.test(this.value)) {
                this.setCustomValidity('Please enter a valid email address');
            } else {
                this.setCustomValidity('');
            }
        });
    }

    // Phone Validation
    const phoneInput = document.getElementById('phone');
    if (phoneInput) {
        phoneInput.addEventListener('blur', function() {
            const phoneRegex = /^[\d\s\(\)\-\+]+$/;
            if (this.value && !phoneRegex.test(this.value)) {
                this.setCustomValidity('Please enter a valid phone number');
            } else {
                this.setCustomValidity('');
            }
        });
    }

    // Join Date Validation
    const joinDateInput = document.getElementById('joinDate');
    if (joinDateInput) {
        joinDateInput.max = new Date().toISOString().split('T')[0];
        joinDateInput.addEventListener('input', function() {
            const selectedDate = new Date(this.value);
            const today = new Date();
            if (selectedDate > today) {
                this.setCustomValidity('Join date cannot be in the future');
            } else {
                this.setCustomValidity('');
            }
        });
    }

    // Salary Validation
    const salaryInput = document.getElementById('salary');
    if (salaryInput) {
        salaryInput.addEventListener('blur', function() {
            if (this.value && parseFloat(this.value) < 0) {
                this.setCustomValidity('Salary cannot be negative');
            } else {
                this.setCustomValidity('');
            }
        });
    }

    // ==================== Handle Delete Modal Submission ====================

    const deleteModals = document.querySelectorAll('.modal');

    deleteModals.forEach(function(modal) {
        modal.addEventListener('show.bs.modal', function(event) {
            const button = event.relatedTarget; // Button that triggered the modal
            const form = this.querySelector('form'); // The form inside the modal
            if (button && form) {
                const employeeId = button.getAttribute('data-employee-id');
                if (employeeId) {
                    // Update the form action dynamically if needed
                    const actionTemplate = form.getAttribute('data-action-template');
                    if (actionTemplate) {
                        form.setAttribute('action', actionTemplate.replace('{id}', employeeId));
                    }
                }
            }
        });
    });

    // ==================== Smooth Confirmations ====================

    const deleteButtons = document.querySelectorAll('.btn-danger[data-bs-toggle="modal"]');
    deleteButtons.forEach(function(btn) {
        btn.addEventListener('click', function() {
            // Optional: Add additional confirmation (if needed)
            // e.g., "Are you absolutely sure?"
        });
    });

});
