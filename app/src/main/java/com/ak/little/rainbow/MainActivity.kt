package com.ak.little.rainbow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ak.little.rainbow.navigation.RainbowDestinations
import com.ak.little.rainbow.navigation.RainbowNavHost
import com.ak.little.rainbow.presentation.login.screen.AdminLoginScreen
import com.ak.little.rainbow.theme.LittleRainbowTheme
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.core.annotation.KoinExperimentalAPI

class MainActivity : ComponentActivity() {
    @OptIn(KoinExperimentalAPI::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LittleRainbowTheme {
                KoinAndroidContext {
                    RainbowNavHost(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background),
                        startDestination = RainbowDestinations.AdminLogin
                    )
                }
            }
        }
    }
}

