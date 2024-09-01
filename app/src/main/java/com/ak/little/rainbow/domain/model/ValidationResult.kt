package com.ak.little.rainbow.domain.model

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
