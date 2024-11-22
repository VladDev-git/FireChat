package com.example.mychat

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.mychat.ui.theme.MyChatTheme
import com.example.mychat.ui_components.MainScreen
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val database = Firebase.database
        val myRef = database.getReference("message")

        //myRef.setValue("Hello, World!")

        setContent {
            MyChatTheme {
                MainScreen() { message ->
                    myRef.setValue(message)
                }
            }
        }
    }
}