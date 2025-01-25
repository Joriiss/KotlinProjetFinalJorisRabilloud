package com.fchps.xmltocompose

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fchps.xmltocompose.composables.MainScreen
import com.fchps.xmltocompose.ui.theme.XmlToComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enables edge-to-edge display, allowing the content to occupy the entire screen area
        enableEdgeToEdge()

        // Set up the UI content for the activity
        setContent {
            XmlToComposeTheme {
                // Scaffold is a layout structure that provides basic Material Design layout
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    // Pass the inner padding and the actions to the MainScreen composable
                    MainScreen(
                        innerPaddingValues = innerPadding, // Padding to apply to the screen content
                        onSubmit = { // Action for when a button (or other event) is triggered
                            Toast.makeText(this, "Go to Next", Toast.LENGTH_LONG).show()
                        },
                        onGoToFragmentsActivity = { // Action to start FragmentsActivity when triggered
                            startActivity(Intent(this, FragmentsActivity::class.java))
                        }
                    )
                }
            }
        }
    }
}

// Composable function that takes a name and displays a greeting text
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", // Display the name in the greeting
        modifier = modifier // Apply any provided modifiers (like padding)
    )
}

// Preview of the Greeting composable to see the result during development
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    XmlToComposeTheme {
        Greeting("Android") // Preview with the name "Android"
    }
}
