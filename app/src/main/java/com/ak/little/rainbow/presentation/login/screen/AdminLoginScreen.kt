package com.ak.little.rainbow.presentation.login.screen

import android.util.Log
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.ak.little.rainbow.R
import com.ak.little.rainbow.presentation.components.ButtonState
import com.ak.little.rainbow.presentation.components.LoadingProgressButton
import com.ak.little.rainbow.presentation.login.components.EmailTextField
import com.ak.little.rainbow.presentation.login.components.PasswordTextField
import com.ak.little.rainbow.presentation.login.viewmodel.AdminLoginUiEvent
import com.ak.little.rainbow.presentation.login.viewmodel.AdminLoginUiState

@Composable
fun AdminLoginScreen(
    modifier: Modifier = Modifier,
    uiState: AdminLoginUiState,
    uiEvent: (AdminLoginUiEvent) -> Unit,
    navigateToDashboard: () -> Unit
) {

    val rainbowColors = listOf(
        Color(0xFFFF0000), // Red
        Color(0xFFFFA500), // Orange
        Color(0xFF008000), // Green
        Color(0xFF0000FF), // Blue
        Color(0xFF4B0082), // Indigo
        Color(0xFFEE82EE)  // Violet
    )

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
                verticalArrangement = Arrangement.spacedBy(12.dp),
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
                    supportingText = uiState.emailErrorMsg
                )

                PasswordTextField(
                    value = uiState.password,
                    onValueChange = {
                        uiEvent(AdminLoginUiEvent.OnPasswordChange(it))
                    },
                    supportingText = uiState.passErrorMsg
                )

                LoadingProgressButton(
                    onClick = {
                        uiEvent(AdminLoginUiEvent.ValidateForm)
                        if (uiState.isPassValidated && uiState.isEmailValidated) {
                            navigateToDashboard()
                        }
                        uiEvent(AdminLoginUiEvent.AdminLogin)
                        uiEvent(AdminLoginUiEvent.ListenAuthStatus)
                    },
                    btnState = ButtonState.IDLE,
                    shape = RoundedCornerShape(35),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Login",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }
}
