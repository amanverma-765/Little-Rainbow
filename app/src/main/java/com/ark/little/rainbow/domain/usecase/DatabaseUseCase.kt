package com.ark.little.rainbow.domain.usecase

import com.ark.little.rainbow.domain.usecase.db.GetAllClasses

data class DatabaseUseCase(
    val getAllClasses: GetAllClasses
)