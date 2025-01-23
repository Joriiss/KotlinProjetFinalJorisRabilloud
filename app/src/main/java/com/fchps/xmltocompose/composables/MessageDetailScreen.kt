package com.fchps.xmltocompose.composables

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import com.fchps.xmltocompose.ui.theme.XmlToComposeTheme
import com.fchps.xmltocompose.data.api.model.MessageResponse

class MessageDetailScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            XmlToComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val messageId = intent.getStringExtra("messageId") ?: ""
                    val messageTitle = intent.getStringExtra("messageTitle") ?: ""
                    val messageBody = intent.getStringExtra("messageBody") ?: ""
                    MessageDetailScreen(
                        MessageResponse(messageId, messageTitle, messageBody)
                    )
                }
            }
        }
    }
}

@Composable
fun MessageDetailScreen(message: MessageResponse) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Text(text = "ID: ${message.id}", fontSize = 20.sp, color = Color.Black)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Title: ${message.title}", fontSize = 20.sp, color = Color.Black)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Body: ${message.body}", fontSize = 16.sp, color = Color.Black)
    }
}

@Preview(showBackground = true)
@Composable
fun MessageDetailScreenPreview() {
    XmlToComposeTheme {
        MessageDetailScreen(
            MessageResponse("1", "Sample Title", "Sample Body")
        )
    }
}