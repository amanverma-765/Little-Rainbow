package com.ak.little.rainbow.presentation.login.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ak.little.rainbow.domain.repo.AdminAuthRepo
import com.ak.little.rainbow.domain.usecase.AdminAuthUseCase
import com.ak.little.rainbow.utils.ApiResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.regex.Pattern


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
        var emailErrorMsg = ""
        val isEmailValidated: Boolean
        val emailPattern = Pattern.compile(
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
        )

        if (adminLoginUiState.value.email.isBlank()) {
            emailErrorMsg = "Email cannot be empty"
            isEmailValidated = false
        } else if (emailPattern.matcher(adminLoginUiState.value.email).matches().not()) {
            emailErrorMsg = "Invalid email address"
            isEmailValidated = false
        } else {
            isEmailValidated = true
        }

        _adminLoginUiState.update { state ->
            state.copy(
                emailErrorMsg = emailErrorMsg,
                isEmailValidated = isEmailValidated
            )
        }
    }

    private fun validatePass() {
        var passErrorMsg = ""
        val isPassValidated: Boolean

        if (adminLoginUiState.value.password.isBlank()) {
            passErrorMsg = "Password cannot be empty"
            isPassValidated = false
        } else {
            isPassValidated = true
        }

        _adminLoginUiState.update { state ->
            state.copy(
                passErrorMsg = passErrorMsg,
                isPassValidated = isPassValidated
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
                Log.e("TAG", "listenAuthStatus: $response")
            }
        }
    }

}
