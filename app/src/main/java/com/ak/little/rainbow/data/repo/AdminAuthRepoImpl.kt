package com.ak.little.rainbow.data.repo

import com.ak.little.rainbow.data.mapper.AuthStatusMapper.toAuthStatus
import com.ak.little.rainbow.data.remote.supabase.SupabaseAuthManager
import com.ak.little.rainbow.domain.model.AuthStatus
import com.ak.little.rainbow.domain.repo.AdminAuthRepo
import com.ak.little.rainbow.utils.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AdminAuthRepoImpl(
    private val supabaseAuthManager: SupabaseAuthManager
): AdminAuthRepo {
    override fun signInWithMagicLink(email: String): Flow<ApiResponse<Unit>> {
        return supabaseAuthManager.signInWithMagicLink(email = email)
    }

    override fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<ApiResponse<Unit>> {
        return supabaseAuthManager.signInWithEmailAndPassword(
            email = email,
            password = password
        )
    }

    override fun adminSignOut(): Flow<ApiResponse<Unit>> {
        return supabaseAuthManager.adminSignOut()
    }

    override fun listenAuthStatus(): Flow<ApiResponse<AuthStatus>> {
        return supabaseAuthManager.listenAuthStatus().map { response ->
            when (response) {
                is ApiResponse.Error -> ApiResponse.Error(response.message)
                is ApiResponse.IDLE -> ApiResponse.IDLE
                is ApiResponse.Loading -> ApiResponse.Loading
                is ApiResponse.Success -> {
                    ApiResponse.Success(response.data.toAuthStatus())
                }
            }
        }
    }
}
