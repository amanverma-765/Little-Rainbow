package com.ak.little.rainbow.domain.usecase.auth

import com.ak.little.rainbow.data.remote.supabase.SupabaseAuthManager
import com.ak.little.rainbow.domain.model.AuthStatus
import com.ak.little.rainbow.domain.repo.AdminAuthRepo
import com.ak.little.rainbow.utils.ApiResponse
import kotlinx.coroutines.flow.Flow

class ListenAuthStatus(
    private val adminAuthRepo: AdminAuthRepo
) {
    operator fun invoke(): Flow<ApiResponse<AuthStatus>> {
        return adminAuthRepo.listenAuthStatus()
    }
}
