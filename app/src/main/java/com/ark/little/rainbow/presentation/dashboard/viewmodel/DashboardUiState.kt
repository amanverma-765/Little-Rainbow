package com.ark.little.rainbow.presentation.dashboard.viewmodel

import com.ark.little.rainbow.domain.model.AuthStatus
import com.ark.little.rainbow.utils.ApiResponse


data class DashboardUiState(

    val adminLogOutResponse: ApiResponse<Unit> = ApiResponse.IDLE,
    val authStatusResponse: ApiResponse<AuthStatus> = ApiResponse.IDLE,

    )
