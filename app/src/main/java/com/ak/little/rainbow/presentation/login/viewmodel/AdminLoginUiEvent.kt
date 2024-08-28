package com.ak.little.rainbow.presentation.login.viewmodel


sealed interface AdminLoginUiEvent {

    data class OnPasswordChange(val text: String) : AdminLoginUiEvent
    data class OnEmailChange(val text: String) : AdminLoginUiEvent
    data object ValidateForm: AdminLoginUiEvent
    data object AdminLogin: AdminLoginUiEvent
    data object ListenAuthStatus: AdminLoginUiEvent

}