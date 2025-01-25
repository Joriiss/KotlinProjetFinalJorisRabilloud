package com.fchps.xmltocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.fchps.xmltocompose.composables.MessageDetailScreen
import com.fchps.xmltocompose.ui.theme.XmlToComposeTheme
import com.fchps.xmltocompose.data.api.model.MessageResponse
import com.fchps.xmltocompose.data.api.model.Name
import com.fchps.xmltocompose.data.api.model.Currency

// Activity class that displays the details of a selected country
class MessageDetailScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enables edge-to-edge display for the activity, ensuring the content fills the entire screen
        enableEdgeToEdge()

        // Set the content for the activity
        setContent {
            // Apply the theme for the app
            XmlToComposeTheme {
                // Scaffold provides a basic layout structure for the screen
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    // Retrieve the country details passed through the Intent from the previous activity
                    val countryName = intent.getStringExtra("countryName") ?: ""
                    val countryCca2 = intent.getStringExtra("countryCca2") ?: ""
                    val countryCapital = intent.getStringExtra("countryCapital") ?: ""
                    val countryRegion = intent.getStringExtra("countryRegion") ?: ""
                    val countryCurrency = intent.getStringExtra("countryCurrency") ?: ""

                    // Pass the retrieved data into the MessageDetailScreen composable for rendering
                    MessageDetailScreen(
                        // Create a MessageResponse object using the retrieved data
                        MessageResponse(
                            name = Name(countryName),
                            cca2 = countryCca2,
                            capital = listOf(countryCapital),
                            region = countryRegion,
                            currencies = mapOf("currency" to Currency(countryCurrency)),
                        ),
                        innerPaddingValues = innerPadding, // Apply padding to the content
                        navController = null // Pass null for the navigation controller in this context (no navigation in this screen)
                    )
                }
            }
        }
    }
}
