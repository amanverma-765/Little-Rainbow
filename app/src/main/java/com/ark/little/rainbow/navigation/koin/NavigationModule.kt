package com.ark.little.rainbow.navigation.koin

import com.ark.little.rainbow.presentation.features.auth.viewmodel.AdminLoginViewModel
import com.ark.little.rainbow.presentation.features.dashboard.viewmodel.DashboardViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val navigationModule = module {

    viewModel {
        AdminLoginViewModel(adminAuthUseCase = get())
    }

    viewModel {
        DashboardViewModel(
            adminAuthUseCase = get(),
            databaseUseCase = get()
        )
    }

}
