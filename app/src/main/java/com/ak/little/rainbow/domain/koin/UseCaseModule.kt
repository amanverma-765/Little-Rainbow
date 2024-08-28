package com.ak.little.rainbow.domain.koin

import com.ak.little.rainbow.domain.usecase.AdminAuthUseCase
import com.ak.little.rainbow.domain.usecase.auth.AdminSignOut
import com.ak.little.rainbow.domain.usecase.auth.ListenAuthStatus
import com.ak.little.rainbow.domain.usecase.auth.SignInWithEmailAndPassword
import com.ak.little.rainbow.domain.usecase.auth.SignInWithMagicLink
import org.koin.dsl.module

val useCaseModule = module {

    single {
        AdminAuthUseCase(
            signInWithMagicLink = SignInWithMagicLink(adminAuthRepo = get()),
            signInWithEmailAndPassword = SignInWithEmailAndPassword(adminAuthRepo = get()),
            adminSignOut = AdminSignOut(adminAuthRepo = get()),
            listenAuthStatus = ListenAuthStatus(adminAuthRepo = get())
        )
    }

}
