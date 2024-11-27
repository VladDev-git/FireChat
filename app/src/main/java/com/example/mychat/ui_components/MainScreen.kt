package com.example.mychat.ui_components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.example.mychat.ui.theme.WhatsAppGreen
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun MainScreen(chatText: String, auth: FirebaseAuth, onSendClick : (String) -> Unit) {
    val coroutineScope = rememberCoroutineScope()
    val message = remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = auth.currentUser?.displayName ?: "MyChat",
                        fontWeight = FontWeight.Bold,
                        color = WhatsAppGreen
                    )
                },
                navigationIcon = {
                    AsyncImage(
                        model = auth.currentUser?.photoUrl,
                        contentDescription = "User Icon",
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        }
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (text, textField, button) = createRefs()

            Text(
                text = chatText,
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
                    message.value = ""
                },
                modifier = Modifier
                    .constrainAs(button) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth()
                    .padding(bottom = 40.dp, start = 16.dp, end = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = WhatsAppGreen
                )
            ) {
                Text(
                    text = "Send"
                )
            }
        }
    }
}






