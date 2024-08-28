package com.ak.little.rainbow.navigation.koin

import com.ak.little.rainbow.presentation.login.viewmodel.AdminLoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val navigationModule = module {

    viewModel {
        AdminLoginViewModel(adminAuthUseCase = get())
    }

}
