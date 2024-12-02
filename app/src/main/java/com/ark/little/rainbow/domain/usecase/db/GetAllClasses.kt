package com.ark.little.rainbow.domain.usecase.db

import com.ark.little.rainbow.domain.model.SchoolClass
import com.ark.little.rainbow.domain.repo.DatabaseManagerRepo
import com.ark.little.rainbow.utils.ApiResponse
import kotlinx.coroutines.flow.Flow

class GetAllClasses(
    private val databaseManagerRepo: DatabaseManagerRepo
) {
    operator fun invoke(): Flow<ApiResponse<List<SchoolClass>>> {
        return databaseManagerRepo.getAllClasses()
    }
}
