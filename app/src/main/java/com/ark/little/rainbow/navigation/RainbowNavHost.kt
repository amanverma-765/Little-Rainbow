package com.ark.little.rainbow.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ark.little.rainbow.navigation.RainbowDestinations
import com.ark.little.rainbow.presentation.dashboard.screen.DashboardScreen
import com.ark.little.rainbow.presentation.auth.screen.AdminLoginScreen
import com.ark.little.rainbow.presentation.auth.viewmodel.AdminLoginViewModel
import com.ark.little.rainbow.presentation.dashboard.viewmodel.DashboardViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun RainbowNavHost(
    modifier: Modifier = Modifier,
    startDestination: RainbowDestinations
) {

    val rootNavigator = rememberNavController()

    NavHost(
        navController = rootNavigator,
        startDestination = startDestination,
        enterTransition = { slideInHorizontally(tween(500)) { it } },
        exitTransition = { scaleOut(targetScale = .8f) + fadeOut() },
        popEnterTransition = { slideInHorizontally(tween(500)) { -it } },
        popExitTransition = { scaleOut(targetScale = 1.2f) + fadeOut() },
        modifier = modifier.fillMaxSize()
    ) {

        composable<RainbowDestinations.AdminLogin> {

            val adminLoginVm = koinViewModel<AdminLoginViewModel>()
            val adminLoginUiState by adminLoginVm.adminLoginUiState.collectAsState()

            AdminLoginScreen(
                uiState = adminLoginUiState,
                uiEvent = adminLoginVm::onEvent,
                navigateToDashboard = {
                    rootNavigator.navigate(RainbowDestinations.Dashboard) {
                        popUpTo(RainbowDestinations.AdminLogin) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable<RainbowDestinations.Dashboard> {

            val dashboardVm = koinViewModel<DashboardViewModel>()
            val dashboardUiState by dashboardVm.dashboardUiState.collectAsState()

            DashboardScreen(
                uiState = dashboardUiState,
                uiEvent = dashboardVm::onEvent,
                navigateToLogin = {
                    rootNavigator.navigate(RainbowDestinations.AdminLogin)
                }
            )
        }
    }
}

