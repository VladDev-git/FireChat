package com.example.mychat

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.mychat.ui.theme.MyChatTheme
import com.example.mychat.ui_components.MainScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {
    lateinit var auth: FirebaseAuth

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val database = Firebase.database
        auth = Firebase.auth
        val myRef = database.getReference("message")

        setContent {
            //val navController = rememberNavController()
            val chatText = remember { mutableStateOf("") }
            val displayName = remember {
                mutableStateOf(auth.currentUser?.displayName ?: "MyChat")
            }

            onChangeListener(myRef) { message ->
                chatText.value += "\n$message"
            }

            MyChatTheme {
                MainScreen(chatText.value, auth, displayName.value, { message ->
                    myRef.setValue(message)
                },{
                    logout()
                })

//                NavHost(navController = navController, startDestination = Routes.AUTHENTICATION_SCREEN) {
//
//                    composable(Routes.AUTHENTICATION_SCREEN) {
//                        AuthenticationScreen() {
//                            //navController.navigate(Routes.MAIN_SCREEN)
//                        }
//                    }
//                    composable(Routes.MAIN_SCREEN) {
//                        MainScreen(chatText.value) { message ->
//                            myRef.setValue(message)
//                        }
//                    }
//                }
            }
        }
    }

    private fun onChangeListener(dRef: DatabaseReference, onNewMessage: (String) -> Unit) {
        dRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val newMessage = snapshot.getValue(String::class.java)
                newMessage?.let { onNewMessage(it) }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun logout() {
        auth.signOut()
        startActivity(Intent(this, AuthenticationActivity::class.java))
        finish()
        return
    }
}







