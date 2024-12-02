package com.ark.little.rainbow.data.repo

import com.ark.little.rainbow.data.mapper.SchoolClassMapper.toSchoolClass
import com.ark.little.rainbow.data.remote.supabase.SupabaseDbManager
import com.ark.little.rainbow.domain.model.SchoolClass
import com.ark.little.rainbow.domain.repo.DatabaseManagerRepo
import com.ark.little.rainbow.utils.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DbManagerRepoImpl(
    private val supabaseDbManager: SupabaseDbManager
) : DatabaseManagerRepo {

    override fun getAllClasses(): Flow<ApiResponse<List<SchoolClass>>> {
        return flow {
            supabaseDbManager.getAllClasses().collect { response ->
                when (response) {
                    is ApiResponse.IDLE -> emit(ApiResponse.IDLE)
                    is ApiResponse.Error -> emit(ApiResponse.Error(response.message))
                    is ApiResponse.Loading -> emit(ApiResponse.Loading)
                    is ApiResponse.Success -> {
                        emit(ApiResponse.Success(response.data.map { it.toSchoolClass() }))
                    }
                }
            }
        }
    }

}