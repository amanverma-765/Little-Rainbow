package com.ark.little.rainbow.domain.usecase.auth

import com.ark.little.rainbow.domain.model.AuthStatus
import com.ark.little.rainbow.domain.repo.AdminAuthRepo
import com.ark.little.rainbow.utils.ApiResponse
import kotlinx.coroutines.flow.Flow

class ListenAuthStatus(
    private val adminAuthRepo: AdminAuthRepo
) {
    operator fun invoke(): Flow<ApiResponse<AuthStatus>> {
        return adminAuthRepo.listenAuthStatus()
    }
}
