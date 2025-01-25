package com.fchps.xmltocompose.composables

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.fchps.xmltocompose.ui.theme.XmlToComposeTheme
import com.fchps.xmltocompose.data.api.model.MessageResponse
import com.fchps.xmltocompose.data.api.model.Name
import com.fchps.xmltocompose.data.api.model.Currency
import androidx.compose.foundation.Image
import coil.compose.rememberAsyncImagePainter
import androidx.navigation.NavController

// A composable to display a row with a label and a corresponding value.
@Composable
fun DetailRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp), // Adds vertical padding between rows.
        horizontalArrangement = Arrangement.SpaceBetween, // Aligns items to the edges.
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$label:", // Displays the label text followed by a colon.
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0D47A1) // Custom blue color for the label.
            ),
            modifier = Modifier.padding(end = 8.dp) // Adds spacing after the label.
        )
        Text(
            text = value, // Displays the corresponding value.
            style = MaterialTheme.typography.bodyMedium.copy(
                color = Color.Black // Black color for the value.
            )
        )
    }
}

// A composable screen to display details about a country.
@Composable
fun MessageDetailScreen(
    country: MessageResponse, // The country details to be displayed.
    innerPaddingValues: PaddingValues, // Padding from surrounding layout.
    navController: NavController? // Navigation controller for navigation actions.
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPaddingValues) // Apply inner padding to the entire screen.
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFFAFAFA), Color(0xFFF5F5F5)) // Gradient background.
                )
            )
            .padding(16.dp), // Padding around the content.
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(), // Card takes full width of the parent.
            colors = CardDefaults.cardColors(containerColor = Color.White), // White card background.
            shape = RoundedCornerShape(16.dp), // Rounded corners for the card.
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp) // Card shadow.
        ) {
            Column(
                modifier = Modifier.padding(16.dp), // Padding inside the card.
                verticalArrangement = Arrangement.spacedBy(12.dp) // Spacing between items.
            ) {
                // Displays the country name and flag in a row.
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = country.name.common, // Country's common name.
                        fontSize = 28.sp, // Font size for the name.
                        color = Color(0xFF0D47A1), // Custom blue color.
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(bottom = 8.dp) // Bottom padding.
                    )
                    Spacer(modifier = Modifier.weight(1f)) // Pushes the flag to the right.
                    Image(
                        painter = rememberAsyncImagePainter("https://flagcdn.com/w320/${country.cca2.lowercase()}.png"),
                        contentDescription = "Flag of ${country.name.common}", // Accessibility description.
                        modifier = Modifier.size(32.dp) // Sets the size of the flag image.
                    )
                }
                // Divider line below the country name and flag.
                HorizontalDivider(thickness = 1.dp, color = Color.LightGray)

                // Details displayed as rows.
                DetailRow(label = "Capital", value = country.capital.joinToString()) // Displays capital.
                DetailRow(label = "Region", value = country.region) // Displays region.
                DetailRow(
                    label = "Currency",
                    value = country.currencies.values.firstOrNull()?.name ?: "N/A" // Handles missing currency.
                )
                DetailRow(label = "Code", value = country.cca2) // Displays country code.

                Spacer(modifier = Modifier.height(16.dp)) // Adds vertical space.

                // Button to navigate back to the previous screen.
                Button(
                    onClick = { navController?.navigateUp() },
                    modifier = Modifier.fillMaxWidth() // Button takes full width.
                ) {
                    Text("Back to List") // Button label.
                }
            }
        }
    }
}

// A preview of the MessageDetailScreen composable for design validation.
@Preview(showBackground = true)
@Composable
fun MessageDetailScreenPreview() {
    XmlToComposeTheme {
        MessageDetailScreen(
            MessageResponse(
                name = Name("South Korea"), // Sample data for the preview.
                cca2 = "KR",
                capital = listOf("Seoul"),
                region = "Asia",
                currencies = mapOf("KRW" to Currency("South Korean won"))
            ),
            innerPaddingValues = PaddingValues(),
            navController = null // Pass null for preview as navigation isn't needed.
        )
    }
}
