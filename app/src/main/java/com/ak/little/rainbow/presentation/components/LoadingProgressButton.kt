package com.ak.little.rainbow.presentation.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Error
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material.icons.rounded.ErrorOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
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
                Icon(
                    imageVector = Icons.Outlined.Error,
                    contentDescription = "Error Icon",
                    modifier = Modifier.size(40.dp)
                )
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
