package com.ark.little.rainbow.presentation.features.auth.viewmodel

import com.ark.little.rainbow.domain.model.AuthStatus
import com.ark.little.rainbow.utils.ApiResponse


data class AdminLoginUiState(

    val adminLoginResponse: ApiResponse<Unit> = ApiResponse.IDLE,
    val authStatusResponse: ApiResponse<AuthStatus> = ApiResponse.IDLE,
    val email: String = "",
    val password: String = "",
    val emailErrorMsg: String? = null,
    val passErrorMsg: String? = null,
    val isEmailValidated: Boolean = false,
    val isPassValidated: Boolean = false

)
