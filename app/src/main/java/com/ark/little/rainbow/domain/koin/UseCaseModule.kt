package com.ark.little.rainbow.domain.koin

import com.ark.little.rainbow.domain.usecase.AdminAuthUseCase
import com.ark.little.rainbow.domain.usecase.DatabaseUseCase
import com.ark.little.rainbow.domain.usecase.auth.AdminSignOut
import com.ark.little.rainbow.domain.usecase.auth.ListenAuthStatus
import com.ark.little.rainbow.domain.usecase.auth.SignInWithEmailAndPassword
import com.ark.little.rainbow.domain.usecase.auth.SignInWithMagicLink
import com.ark.little.rainbow.domain.usecase.auth.validation.ValidateEmail
import com.ark.little.rainbow.domain.usecase.auth.validation.ValidatePassword
import com.ark.little.rainbow.domain.usecase.db.GetAllClasses
import org.koin.dsl.module

val useCaseModule = module {

    single {
        AdminAuthUseCase(
            signInWithMagicLink = SignInWithMagicLink(adminAuthRepo = get()),
            signInWithEmailAndPassword = SignInWithEmailAndPassword(adminAuthRepo = get()),
            adminSignOut = AdminSignOut(adminAuthRepo = get()),
            listenAuthStatus = ListenAuthStatus(adminAuthRepo = get()),
            validatePassword = ValidatePassword(),
            validateEmail = ValidateEmail()
        )
    }

    single {
        DatabaseUseCase(
            getAllClasses = GetAllClasses(databaseManagerRepo = get())
        )
    }

}
