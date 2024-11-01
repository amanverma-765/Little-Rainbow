package com.ark.little.rainbow.presentation.dashboard.viewmodel


sealed interface DashboardUiEvent {

    data object AdminLogOut: DashboardUiEvent
    data object ListenAuthStatus: DashboardUiEvent

}
