package com.ark.little.rainbow.presentation.features.dashboard.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ark.little.rainbow.domain.model.AuthStatus
import com.ark.little.rainbow.domain.model.SchoolClass
import com.ark.little.rainbow.presentation.features.dashboard.viewmodel.DashboardUiEvent
import com.ark.little.rainbow.presentation.features.dashboard.viewmodel.DashboardUiState
import com.ark.little.rainbow.utils.ApiResponse
import com.ark.little.rainbow.utils.toast

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    uiState: DashboardUiState,
    uiEvent: (DashboardUiEvent) -> Unit,
    navigateToLogin: () -> Unit
) {

    val context = LocalContext.current
    var classes by remember { mutableStateOf(emptyList<SchoolClass>()) }
    val loadingState by remember {
        derivedStateOf {
            uiState.getClassesResponse is ApiResponse.Loading
                    || uiState.authStatusResponse is ApiResponse.Loading
        }
    }

    LaunchedEffect(key1 = Unit) {
        uiEvent(DashboardUiEvent.ListenAuthStatus)
        uiEvent(DashboardUiEvent.GetAllClasses)
    }

    LaunchedEffect(key1 = uiState.getClassesResponse) {
        when (val schoolClasses = uiState.getClassesResponse) {
            is ApiResponse.Error -> context.toast("Failed while fetching student classes")
            is ApiResponse.Success -> classes = schoolClasses.data
            else -> Unit
        }
    }

    LaunchedEffect(key1 = uiState.authStatusResponse) {

        when (val response = uiState.authStatusResponse) {
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

            else -> Unit
        }
    }

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    navigationIcon = {
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Default.Menu, contentDescription = "Menu Button"
                            )
                        }
                    },
                    title = {
                        Text(text = "Little Rainbow")
                    },
                    actions = {
                        IconButton(
                            onClick = {
                                uiEvent(DashboardUiEvent.AdminLogOut)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.Logout,
                                contentDescription = "Log Out"
                            )
                        }
                    }
                )
                if (loadingState) LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }
        }
    ) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            item(span = { GridItemSpan(maxLineSpan) }) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = "Stats",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.ExtraBold,
                        )
                    }
                }
            }

            items(classes) { classItem ->
                Card(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = classItem.name,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.ExtraBold,
                        )
                    }
                }
            }
        }
    }
}
