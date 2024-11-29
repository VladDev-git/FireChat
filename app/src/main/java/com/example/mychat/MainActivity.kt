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

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "MutableCollectionMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val database = Firebase.database
        auth = Firebase.auth
        val myRef = database.getReference("message")

        setContent {
            val chatList = remember { mutableStateOf(ArrayList<User>()) }
            val displayName = remember {
                mutableStateOf(auth.currentUser?.displayName ?: "MyChat")
            }

            onChangeListener(myRef) { list ->
                chatList.value = list
            }

            MyChatTheme {
                MainScreen(chatList.value, auth, displayName.value, { message ->
                    myRef.child(myRef.push().key ?: "message")
                        .setValue(User(auth.currentUser?.displayName, message))
                },{
                    logout()
                })
            }
        }
    }

    private fun onChangeListener(dRef: DatabaseReference, onUpdateList: (ArrayList<User>) -> Unit) {
        dRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = ArrayList<User>()
                for (s in snapshot.children) {
                    val user = s.getValue(User::class.java)
                    if (user != null) {
                        list.add(user)
                    }
                }
                onUpdateList(list)
            }

            override fun onCancelled(error: DatabaseError) {
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







