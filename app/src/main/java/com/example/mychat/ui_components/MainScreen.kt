package com.example.mychat.ui_components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun MainScreen(onSendClick : (String) -> Unit) {
    val message = mutableStateOf("")

    Scaffold(modifier = Modifier.fillMaxSize()) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (text, textField, button) = createRefs()

            Text(
                text = "Hello, World!",
                modifier = Modifier
                    .constrainAs(text) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .padding(20.dp, top = 40.dp)
                    .fillMaxSize()
            )
            TextField(
                value = message.value,
                onValueChange = {
                    message.value = it
                },
                label = {
                    Text(
                        text = "Message"
                    )
                },
                modifier = Modifier
                    .constrainAs(textField) {
                        bottom.linkTo(button.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth()
                    .padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
            )
            Button(
                onClick = {
                    onSendClick(message.value)
                },
                modifier = Modifier
                    .constrainAs(button) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth()
                    .padding(bottom = 40.dp, start = 16.dp, end = 16.dp)
            ) {
                Text(
                    text = "Send"
                )
            }
        }
    }
}