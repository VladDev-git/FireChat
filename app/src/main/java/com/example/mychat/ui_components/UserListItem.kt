package com.example.mychat.ui_components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mychat.User
import com.example.mychat.ui.theme.WhatsAppGreen

@Composable
fun UserListItem(userItem: User) {
    Card(
        modifier = Modifier.padding(start = 5.dp, end = 5.dp, top = 5.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = WhatsAppGreen
        )
    ) {
        Column(
            modifier = Modifier.padding(5.dp)
        ) {
            Text(
                text = userItem.name ?: "Name",
                color = Color.Black,
                fontSize = 12.sp
            )
            Text(
                text = userItem.message ?: "Message",
                color = Color.Black
            )
        }
    }
}