package com.example.mychat.ui_components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.mychat.ui.theme.WhatsAppGreen


@Composable
fun AuthenticationScreen(onSignClick: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                onSignClick()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = WhatsAppGreen
            )
        ) {
            Text(text = "Sign in with Google")
        }
    }
}
