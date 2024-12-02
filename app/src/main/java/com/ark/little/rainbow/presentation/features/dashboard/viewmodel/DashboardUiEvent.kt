package com.ark.little.rainbow.presentation.features.dashboard.viewmodel


sealed interface DashboardUiEvent {

    data object AdminLogOut : DashboardUiEvent
    data object ListenAuthStatus : DashboardUiEvent
    data object GetAllClasses : DashboardUiEvent

}
