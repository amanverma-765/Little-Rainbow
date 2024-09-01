package com.ak.little.rainbow.presentation.auth.screen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.ak.little.rainbow.R
import com.ak.little.rainbow.domain.model.AuthStatus
import com.ak.little.rainbow.presentation.auth.components.EmailTextField
import com.ak.little.rainbow.presentation.auth.components.PasswordTextField
import com.ak.little.rainbow.presentation.auth.viewmodel.AdminLoginUiEvent
import com.ak.little.rainbow.presentation.auth.viewmodel.AdminLoginUiState
import com.ak.little.rainbow.presentation.components.ButtonState
import com.ak.little.rainbow.presentation.components.LoadingProgressButton
import com.ak.little.rainbow.presentation.dashboard.viewmodel.DashboardUiEvent
import com.ak.little.rainbow.utils.ApiResponse
import com.ak.little.rainbow.utils.toast

@Composable
fun AdminLoginScreen(
    modifier: Modifier = Modifier,
    uiState: AdminLoginUiState,
    uiEvent: (AdminLoginUiEvent) -> Unit,
    navigateToDashboard: () -> Unit
) {

    val context = LocalContext.current
    val errorMsg = remember { mutableStateOf("") }

    val rainbowColors = listOf(
        Color(0xFFFF0000), // Red
        Color(0xFFFFA500), // Orange
        Color(0xFF008000), // Green
        Color(0xFF0000FF), // Blue
        Color(0xFF4B0082), // Indigo
        Color(0xFFEE82EE)  // Violet
    )

    LaunchedEffect(key1 = Unit) {
        uiEvent(AdminLoginUiEvent.ListenAuthStatus)
        when (val response = uiState.authStatusResponse) {
            is ApiResponse.IDLE -> Unit
            is ApiResponse.Loading -> Unit
            is ApiResponse.Error -> {
                context.toast("Something went wrong")
            }
            is ApiResponse.Success -> {
                when (response.data) {
                    is AuthStatus.Authenticated -> {
                        context.toast("Authenticated Successfully")
                        navigateToDashboard()
                    }

                    is AuthStatus.NotAuthenticated -> {
                        context.toast("User is not authenticated")
                    }
                }
            }
        }
    }

    LaunchedEffect(key1 = uiState.adminLoginResponse) {
        when (val response = uiState.adminLoginResponse) {
            is ApiResponse.IDLE -> Unit
            is ApiResponse.Loading -> Unit
            is ApiResponse.Error -> {
                context.toast(response.message)
                if (response.message?.length!! < 100) {
                    errorMsg.value = response.message
                }
            }
            is ApiResponse.Success -> {
                navigateToDashboard()
            }
        }
    }

    Scaffold { innerPadding ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp)
                .imePadding()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.widthIn(max = 400.dp)
            ) {

                Image(
                    painter = painterResource(id = R.drawable.rainbow),
                    contentDescription = "Rainbow Image",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.wrapContentSize()
                )

                val littleRainbow = buildAnnotatedString {
                    "Little Rainbow".forEachIndexed { index, char ->
                        withStyle(style = TextStyle(color = rainbowColors[index % rainbowColors.size]).toSpanStyle()) {
                            append(char)
                        }
                    }
                }

                Text(
                    text = littleRainbow,
                    style = MaterialTheme.typography.displaySmall,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold
                )

                EmailTextField(
                    value = uiState.email,
                    onValueChange = {
                        uiEvent(AdminLoginUiEvent.OnEmailChange(it))
                    },
                    supportingText = uiState.emailErrorMsg,
                    modifier = Modifier.animateContentSize()
                )

                PasswordTextField(
                    value = uiState.password,
                    onValueChange = {
                        uiEvent(AdminLoginUiEvent.OnPasswordChange(it))
                    },
                    supportingText = uiState.passErrorMsg,
                    modifier = Modifier.animateContentSize()
                )

                LoadingProgressButton(
                    onClick = {
                        uiEvent(AdminLoginUiEvent.ValidateForm)
                        if (uiState.isPassValidated && uiState.isEmailValidated) {
                            uiEvent(AdminLoginUiEvent.AdminLogin)
                        }
                    },
                    btnState = when (uiState.adminLoginResponse) {
                        is ApiResponse.Error -> ButtonState.ERROR
                        is ApiResponse.IDLE -> ButtonState.IDLE
                        is ApiResponse.Loading -> ButtonState.LOADING
                        is ApiResponse.Success -> ButtonState.SUCCESS
                    },
                    shape = RoundedCornerShape(35),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Login",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                    )
                }

                Text(
                    text = errorMsg.value,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.error,
                )
            }
        }
    }
}
