package com.ark.little.rainbow.presentation.auth.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Password
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation


@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    supportingText: String?
) {

    var passVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        supportingText = if (supportingText != null) {
            {
                Text(
                    text = supportingText,
                    color = MaterialTheme.colorScheme.error
                )
            }
        } else null,
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Password,
                contentDescription = "Password Icon",
                tint = MaterialTheme.colorScheme.tertiary,
            )
        },
        placeholder = {
            Text(text = "Enter your password")
        },
        trailingIcon = {
            AnimatedVisibility(
                visible = passVisible,
                exit = scaleOut(),
                enter = scaleIn()
            ) {
                Icon(
                    imageVector = Icons.Rounded.VisibilityOff,
                    contentDescription = "Password Visibility",
                    tint = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.clickable { passVisible = false }
                )
            }

            AnimatedVisibility(
                visible = passVisible.not(),
                exit = scaleOut(),
                enter = scaleIn()
            ) {
                Icon(
                    imageVector = Icons.Rounded.Visibility,
                    contentDescription = "Password Visibility",
                    tint = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.clickable { passVisible = true }
                )
            }
        },
        visualTransformation = if (passVisible) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        maxLines = 1,
        shape = RoundedCornerShape(45),
        modifier = modifier.fillMaxWidth()
    )
}

