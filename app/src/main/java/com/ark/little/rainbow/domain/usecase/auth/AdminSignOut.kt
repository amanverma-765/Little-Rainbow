package com.ark.little.rainbow.domain.usecase.auth

import com.ark.little.rainbow.domain.repo.AdminAuthRepo
import com.ark.little.rainbow.utils.ApiResponse
import kotlinx.coroutines.flow.Flow

class AdminSignOut(
    private val adminAuthRepo: AdminAuthRepo
) {
    operator fun invoke(): Flow<ApiResponse<Unit>> {
        return adminAuthRepo.adminSignOut()
    }
}
