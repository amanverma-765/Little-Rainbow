package com.ark.little.rainbow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import com.ark.little.rainbow.navigation.RainbowDestinations
import com.ark.little.rainbow.navigation.RainbowNavHost
import com.ark.little.rainbow.theme.LittleRainbowTheme
import org.koin.androidx.compose.KoinAndroidContext

class MainActivity : ComponentActivity() {
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
                        startDestination = RainbowDestinations.Dashboard
                    )
                }
            }
        }
    }
}
