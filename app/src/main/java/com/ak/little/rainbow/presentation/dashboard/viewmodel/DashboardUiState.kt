package com.ak.little.rainbow.presentation.dashboard.viewmodel

import com.ak.little.rainbow.domain.model.AuthStatus
import com.ak.little.rainbow.utils.ApiResponse


data class DashboardUiState(

    val adminLogOutResponse: ApiResponse<Unit> = ApiResponse.IDLE,
    val authStatusResponse: ApiResponse<AuthStatus> = ApiResponse.IDLE,

)
