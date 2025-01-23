package com.fchps.xmltocompose.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fchps.xmltocompose.data.api.model.MessageResponse
import com.fchps.xmltocompose.repository.MessageRepository
import com.fchps.xmltocompose.viewmodel.MessageViewModel
import com.fchps.xmltocompose.viewmodel.MessageViewModelFactory
import androidx.compose.foundation.clickable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavType
import androidx.navigation.navArgument

@Composable
fun MessageScreen(
    messageRepository: MessageRepository,
    innerPaddingValues: PaddingValues
) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "messageList") {
        composable("messageList") {
            MessageListScreen(messageRepository, innerPaddingValues, navController)
        }
        composable(
            "messageDetail/{messageId}/{messageTitle}/{messageBody}",
            arguments = listOf(
                navArgument("messageId") { type = NavType.StringType },
                navArgument("messageTitle") { type = NavType.StringType },
                navArgument("messageBody") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val messageId = backStackEntry.arguments?.getString("messageId") ?: ""
            val messageTitle = backStackEntry.arguments?.getString("messageTitle") ?: ""
            val messageBody = backStackEntry.arguments?.getString("messageBody") ?: ""
            MessageDetailScreen(MessageResponse(messageId, messageTitle, messageBody))
        }
    }
}

@Composable
fun MessageListScreen(
    messageRepository: MessageRepository,
    innerPaddingValues: PaddingValues,
    navController: NavController
) {
    val viewModel: MessageViewModel = viewModel(factory = MessageViewModelFactory(messageRepository))
    val messages by viewModel.messages.observeAsState(emptyList())

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(innerPaddingValues)
        .background(Color.White)
        .padding(16.dp)) {
        Column {

            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(messages) { message ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate("messageDetail/${message.id}/${message.title}/${message.body}")
                            },
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD))
                    ) {
                        Text(
                            text = message.title,
                            modifier = Modifier.padding(16.dp),
                            fontSize = 16.sp,
                            color = Color(0xFF0D47A1)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    viewModel.addMessage()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("See Next Country")
            }
        }
    }
}