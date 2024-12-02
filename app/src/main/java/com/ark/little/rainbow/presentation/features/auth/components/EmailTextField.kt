package com.ark.little.rainbow.presentation.features.auth.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType


@Composable
fun EmailTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    supportingText: String?
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
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
                imageVector = Icons.Rounded.Email,
                contentDescription = "Email Icon",
                tint = MaterialTheme.colorScheme.tertiary,
            )
        },
        placeholder = {
            Text(text = "Enter your email address")
        },
        maxLines = 1,
        shape = RoundedCornerShape(45),
        modifier = modifier.fillMaxWidth()
    )
}

