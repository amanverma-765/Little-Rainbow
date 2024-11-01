package com.ark.little.rainbow.presentation.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ark.little.rainbow.domain.usecase.AdminAuthUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class AdminLoginViewModel(
    private val adminAuthUseCase: AdminAuthUseCase
) : ViewModel() {

    private val _adminLoginUiState = MutableStateFlow(AdminLoginUiState())
    val adminLoginUiState = _adminLoginUiState.asStateFlow()

    fun onEvent(event: AdminLoginUiEvent) {
        when (event) {
            is AdminLoginUiEvent.OnEmailChange -> handleEmailChange(event.text)
            is AdminLoginUiEvent.OnPasswordChange -> handlePasswordChange(event.text)
            is AdminLoginUiEvent.AdminLogin -> adminLogin()
            is AdminLoginUiEvent.ListenAuthStatus -> listenAuthStatus()
            is AdminLoginUiEvent.ValidateForm -> {
                validateEmail()
                validatePass()
            }
        }
    }

    private fun handlePasswordChange(pass: String) {
        _adminLoginUiState.update { state ->
            state.copy(
                password = pass
            )
        }
        validatePass()
    }

    private fun handleEmailChange(email: String) {
        _adminLoginUiState.update { state ->
            state.copy(
                email = email
            )
        }
        validateEmail()
    }

    private fun validateEmail() {

        val emailResult = adminAuthUseCase.validateEmail.validate(adminLoginUiState.value.email)

        _adminLoginUiState.update { state ->
            state.copy(
                emailErrorMsg = emailResult.errorMessage,
                isEmailValidated = emailResult.successful
            )
        }
    }

    private fun validatePass() {

        val passResult = adminAuthUseCase.validatePassword.validate(adminLoginUiState.value.password)

        _adminLoginUiState.update { state ->
            state.copy(
                passErrorMsg = passResult.errorMessage,
                isPassValidated = passResult.successful
            )
        }
    }

    private fun adminLogin() {
        viewModelScope.launch {
            adminAuthUseCase.signInWithEmailAndPassword(
                email = adminLoginUiState.value.email,
                password = adminLoginUiState.value.password
            ).collect { response ->
                _adminLoginUiState.update { state ->
                    state.copy(
                        adminLoginResponse = response
                    )
                }
            }
        }
    }

    private fun listenAuthStatus() {
        viewModelScope.launch {
            adminAuthUseCase.listenAuthStatus().collect { response ->
                _adminLoginUiState.update { state ->
                    state.copy(
                        authStatusResponse = response
                    )
                }
            }
        }
    }

}
