package com.ak.little.rainbow.presentation.dashboard.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.ak.little.rainbow.domain.model.AuthStatus
import com.ak.little.rainbow.presentation.dashboard.viewmodel.DashboardUiEvent
import com.ak.little.rainbow.presentation.dashboard.viewmodel.DashboardUiState
import com.ak.little.rainbow.utils.ApiResponse
import com.ak.little.rainbow.utils.toast

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    uiState: DashboardUiState,
    uiEvent: (DashboardUiEvent) -> Unit,
    navigateToLogin: () -> Unit
) {

    val context = LocalContext.current

    LaunchedEffect(key1 = Unit) {
        uiEvent(DashboardUiEvent.ListenAuthStatus)
    }

    LaunchedEffect(key1 = uiState.authStatusResponse) {

        when (val response = uiState.authStatusResponse) {
            is ApiResponse.IDLE -> Unit
            is ApiResponse.Loading -> Unit
            is ApiResponse.Error -> {
                context.toast("Something went wrong")
                navigateToLogin()
            }

            is ApiResponse.Success -> {
                when (response.data) {
                    is AuthStatus.Authenticated -> {
                        context.toast("Authenticated Successfully")
                    }

                    is AuthStatus.NotAuthenticated -> {
                        context.toast("User is not authenticated")
                        navigateToLogin()
                    }
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu Button"
                        )
                    }
                },
                title = {
                    Text(text = "Little Rainbow")
                },
            )
        }
    ) { innerPadding ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column {
                Text(text = "I am Dashboard", style = MaterialTheme.typography.titleLarge)

                Button(onClick = { uiEvent(DashboardUiEvent.AdminLogOut) }) {
                    Text("Log Out")
                }
            }
        }
    }
}
