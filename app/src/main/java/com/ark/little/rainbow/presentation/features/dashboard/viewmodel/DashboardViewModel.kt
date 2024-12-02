package com.ark.little.rainbow.presentation.features.dashboard.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ark.little.rainbow.domain.usecase.AdminAuthUseCase
import com.ark.little.rainbow.domain.usecase.DatabaseUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class DashboardViewModel(
    private val adminAuthUseCase: AdminAuthUseCase,
    private val databaseUseCase: DatabaseUseCase
) : ViewModel() {

    private val _dashboardUiState = MutableStateFlow(DashboardUiState())
    val dashboardUiState = _dashboardUiState.asStateFlow()

    fun onEvent(event: DashboardUiEvent) {
        when (event) {
            is DashboardUiEvent.AdminLogOut -> adminLogOut()
            is DashboardUiEvent.ListenAuthStatus -> listenAuthStatus()
            is DashboardUiEvent.GetAllClasses -> getAllClasses()
        }
    }

    private fun getAllClasses() {
        viewModelScope.launch {
            databaseUseCase.getAllClasses().collect { response ->
                _dashboardUiState.update { state ->
                    state.copy(
                        getClassesResponse = response
                    )
                }
            }
        }
    }

    private fun adminLogOut() {
        viewModelScope.launch {
            adminAuthUseCase.adminSignOut().collect { response ->
                _dashboardUiState.update { state ->
                    state.copy(
                        adminLogOutResponse = response
                    )
                }
            }
        }
    }

    private fun listenAuthStatus() {
        viewModelScope.launch {
            adminAuthUseCase.listenAuthStatus().collect { response ->
                _dashboardUiState.update { state ->
                    state.copy(
                        authStatusResponse = response
                    )
                }
            }
        }
    }

}
