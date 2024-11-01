package com.ark.little.rainbow.domain.usecase.auth.validation

import com.ark.little.rainbow.domain.model.ValidationResult
import java.util.regex.Pattern

class ValidateEmail {

    fun validate(email: String): ValidationResult {

        val emailPattern = Pattern.compile(
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
        )

        if (email.isBlank() || email.isEmpty()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Email cannot be empty"
            )
        }

        if (emailPattern.matcher(email).matches().not()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Invalid email address"
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}
