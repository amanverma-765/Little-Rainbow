package com.ark.little.rainbow.navigation.koin

import com.ark.little.rainbow.presentation.auth.viewmodel.AdminLoginViewModel
import com.ark.little.rainbow.presentation.dashboard.viewmodel.DashboardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val navigationModule = module {

    viewModel {
        AdminLoginViewModel(adminAuthUseCase = get())
    }

    viewModel {
        DashboardViewModel(adminAuthUseCase = get())
    }

}
