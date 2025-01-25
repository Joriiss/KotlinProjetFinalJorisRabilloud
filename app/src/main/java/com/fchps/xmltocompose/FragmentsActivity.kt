package com.fchps.xmltocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import com.fchps.xmltocompose.composables.MessageScreen
import com.fchps.xmltocompose.data.api.MessageApi
import com.fchps.xmltocompose.data.remote.MessageRepositoryImpl
import com.fchps.xmltocompose.ui.theme.XmlToComposeTheme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.fchps.xmltocompose.data.api.RetroFitSingleton

class FragmentsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Retrofit instance using the singleton pattern
        val retrofit = RetroFitSingleton.getInstance()

        // Create the MessageApi interface to define API calls
        val api = retrofit.create(MessageApi::class.java)

        // Create the repository that will handle the communication between ViewModel and the API
        val repository = MessageRepositoryImpl(api)

        // Set up the composable content of the activity
        setContent {
            XmlToComposeTheme {
                // Scaffold is the main layout structure for your UI
                Scaffold { innerPadding ->

                    // Pass the message repository and inner padding to the MessageScreen composable
                    MessageScreen(
                        innerPaddingValues = innerPadding, // Padding values for proper layout spacing
                        messageRepository = repository // Provide the repository for fetching country data
                    )
                }
            }
        }
    }
}
