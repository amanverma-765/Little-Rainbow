package com.ark.little.rainbow.domain.repo

import com.ark.little.rainbow.domain.model.SchoolClass
import com.ark.little.rainbow.utils.ApiResponse
import kotlinx.coroutines.flow.Flow

interface DatabaseManagerRepo {

    fun getAllClasses(): Flow<ApiResponse<List<SchoolClass>>>

}