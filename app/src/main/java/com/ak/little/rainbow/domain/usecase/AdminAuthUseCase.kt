package com.ak.little.rainbow.domain.usecase

import com.ak.little.rainbow.domain.usecase.auth.AdminSignOut
import com.ak.little.rainbow.domain.usecase.auth.ListenAuthStatus
import com.ak.little.rainbow.domain.usecase.auth.SignInWithEmailAndPassword
import com.ak.little.rainbow.domain.usecase.auth.SignInWithMagicLink
import com.ak.little.rainbow.domain.usecase.auth.validation.ValidateEmail
import com.ak.little.rainbow.domain.usecase.auth.validation.ValidatePassword

data class AdminAuthUseCase(
    val signInWithMagicLink: SignInWithMagicLink,
    val signInWithEmailAndPassword: SignInWithEmailAndPassword,
    val adminSignOut: AdminSignOut,
    val listenAuthStatus: ListenAuthStatus,
    val validateEmail: ValidateEmail,
    val validatePassword: ValidatePassword
)
