package com.ark.little.rainbow.domain.usecase

import com.ark.little.rainbow.domain.usecase.auth.AdminSignOut
import com.ark.little.rainbow.domain.usecase.auth.ListenAuthStatus
import com.ark.little.rainbow.domain.usecase.auth.SignInWithEmailAndPassword
import com.ark.little.rainbow.domain.usecase.auth.SignInWithMagicLink
import com.ark.little.rainbow.domain.usecase.auth.validation.ValidateEmail
import com.ark.little.rainbow.domain.usecase.auth.validation.ValidatePassword

data class AdminAuthUseCase(
    val signInWithMagicLink: SignInWithMagicLink,
    val signInWithEmailAndPassword: SignInWithEmailAndPassword,
    val adminSignOut: AdminSignOut,
    val listenAuthStatus: ListenAuthStatus,
    val validateEmail: ValidateEmail,
    val validatePassword: ValidatePassword
)
