package com.ak.little.rainbow.domain.usecase.auth

import com.ak.little.rainbow.domain.repo.AdminAuthRepo
import com.ak.little.rainbow.utils.ApiResponse
import kotlinx.coroutines.flow.Flow

class AdminSignOut(
    private val adminAuthRepo: AdminAuthRepo
) {
    operator fun invoke(): Flow<ApiResponse<Unit>> {
        return adminAuthRepo.adminSignOut()
    }
}
