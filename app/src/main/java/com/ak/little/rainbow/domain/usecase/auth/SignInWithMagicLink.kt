package com.ak.little.rainbow.domain.usecase.auth

import com.ak.little.rainbow.domain.repo.AdminAuthRepo
import com.ak.little.rainbow.utils.ApiResponse
import kotlinx.coroutines.flow.Flow

class SignInWithMagicLink(
    private val adminAuthRepo: AdminAuthRepo
) {
    operator fun invoke(email: String): Flow<ApiResponse<Unit>> {
        return adminAuthRepo.signInWithMagicLink(email)
    }
}
