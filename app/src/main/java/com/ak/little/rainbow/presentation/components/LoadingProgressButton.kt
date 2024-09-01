package com.ak.little.rainbow.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp


@Composable
fun LoadingProgressButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    btnState: ButtonState,
    shape: Shape = ButtonDefaults.shape,
    content: @Composable () -> Unit
) {

    Button(
        onClick = onClick,
        shape = shape,
        modifier = modifier
    ) {
        when (btnState) {
            ButtonState.IDLE -> {
                content()
            }
            ButtonState.LOADING -> {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(40.dp)
                )
            }
            ButtonState.ERROR -> {
               content()
            }
            ButtonState.SUCCESS -> {
                content()
            }
        }
    }
}


enum class ButtonState {
    IDLE,
    LOADING,
    ERROR,
    SUCCESS
}
