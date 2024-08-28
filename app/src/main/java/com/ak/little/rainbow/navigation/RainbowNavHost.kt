package com.ak.little.rainbow.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ak.little.rainbow.presentation.dashboard.screen.DashboardScreen
import com.ak.little.rainbow.presentation.login.screen.AdminLoginScreen
import com.ak.little.rainbow.presentation.login.viewmodel.AdminLoginViewModel
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
        exitTransition = { slideOutHorizontally(tween(500)) { -it } },
        popEnterTransition = { slideInHorizontally(tween(500)) { -it } },
        popExitTransition = { slideOutHorizontally(tween(500)) { it } },
        modifier = modifier.fillMaxSize()
    ) {

        composable<RainbowDestinations.AdminLogin> {

            val adminLoginVm = koinViewModel<AdminLoginViewModel>()
            val adminLoginUiState by adminLoginVm.adminLoginUiState.collectAsState()

            AdminLoginScreen(
                uiState = adminLoginUiState,
                uiEvent = adminLoginVm::onEvent,
                navigateToDashboard = {
                    rootNavigator.navigate(RainbowDestinations.Dashboard)
                }
            )
        }

        composable<RainbowDestinations.Dashboard> {
            DashboardScreen()
        }
    }
}

