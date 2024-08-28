package com.ak.little.rainbow.domain.repo

import com.ak.little.rainbow.domain.model.AuthStatus
import com.ak.little.rainbow.utils.ApiResponse
import kotlinx.coroutines.flow.Flow


interface AdminAuthRepo {

    fun signInWithMagicLink(email: String): Flow<ApiResponse<Unit>>

    fun signInWithEmailAndPassword(email: String, password: String): Flow<ApiResponse<Unit>>

    fun adminSignOut(): Flow<ApiResponse<Unit>>

    fun listenAuthStatus(): Flow<ApiResponse<AuthStatus>>
}
