package com.ak.little.rainbow.presentation.login.viewmodel

import com.ak.little.rainbow.domain.model.AuthStatus
import com.ak.little.rainbow.utils.ApiResponse


data class AdminLoginUiState(

    val adminLoginResponse: ApiResponse<Unit> = ApiResponse.IDLE,
    val authStatusResponse: ApiResponse<AuthStatus> = ApiResponse.IDLE,
    val email: String = "",
    val password: String = "",
    val emailErrorMsg: String = "",
    val passErrorMsg: String = "",
    val isEmailValidated: Boolean = false,
    val isPassValidated: Boolean = false

)
