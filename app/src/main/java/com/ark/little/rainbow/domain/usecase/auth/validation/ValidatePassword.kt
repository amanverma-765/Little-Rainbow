package com.ark.little.rainbow.domain.usecase.auth.validation

import com.ark.little.rainbow.domain.model.ValidationResult

class ValidatePassword {

    fun validate(pass: String): ValidationResult {
        if (pass.isBlank() || pass.isEmpty()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password cannot be empty"
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}
