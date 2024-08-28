package com.ak.little.rainbow.presentation.login.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun EmailTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    supportingText: String
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        supportingText = {
            Text(
                text = supportingText,
                color = MaterialTheme.colorScheme.error
            )
        },
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

