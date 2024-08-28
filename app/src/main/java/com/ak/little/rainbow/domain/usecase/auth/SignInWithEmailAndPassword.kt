package com.ak.little.rainbow.domain.usecase.auth

import com.ak.little.rainbow.domain.repo.AdminAuthRepo
import com.ak.little.rainbow.utils.ApiResponse
import kotlinx.coroutines.flow.Flow

class SignInWithEmailAndPassword(
    private val adminAuthRepo: AdminAuthRepo
) {
    operator fun invoke(email: String, password: String): Flow<ApiResponse<Unit>> {
        return adminAuthRepo.signInWithEmailAndPassword(
            email = email,
            password = password
        )
    }
}
