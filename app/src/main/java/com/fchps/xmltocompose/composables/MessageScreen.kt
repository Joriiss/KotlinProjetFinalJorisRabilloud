package com.fchps.xmltocompose.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fchps.xmltocompose.data.api.model.MessageResponse
import com.fchps.xmltocompose.viewmodel.MessageViewModel
import com.fchps.xmltocompose.viewmodel.MessageViewModelFactory
import androidx.compose.foundation.clickable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.fchps.xmltocompose.data.remote.MessageRepositoryImpl
import com.fchps.xmltocompose.data.api.model.Name
import com.fchps.xmltocompose.data.api.model.Currency
import androidx.compose.runtime.livedata.observeAsState
import coil.compose.rememberAsyncImagePainter
import androidx.compose.ui.Alignment

@Composable
fun MessageScreen(
    messageRepository: MessageRepositoryImpl,
    innerPaddingValues: PaddingValues
) {
    // Create a navigation controller to manage navigation between screens
    val navController = rememberNavController()

    // Define a navigation host with two screens: a list screen and a detail screen
    NavHost(navController, startDestination = "countryList") {
        // Composable for the country list screen
        composable("countryList") {
            CountryListScreen(messageRepository, innerPaddingValues, navController)
        }
        // Composable for the country detail screen, with arguments passed via the route
        composable(
            "countryDetail/{countryName}/{countryCca2}/{countryCapital}/{countryRegion}/{countryCurrency}",
            arguments = listOf(
                navArgument("countryName") { type = NavType.StringType },
                navArgument("countryCca2") { type = NavType.StringType },
                navArgument("countryCapital") { type = NavType.StringType },
                navArgument("countryRegion") { type = NavType.StringType },
                navArgument("countryCurrency") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            // Retrieve arguments passed from the list screen
            val countryName = backStackEntry.arguments?.getString("countryName") ?: ""
            val countryCca2 = backStackEntry.arguments?.getString("countryCca2") ?: ""
            val countryCapital = backStackEntry.arguments?.getString("countryCapital") ?: ""
            val countryRegion = backStackEntry.arguments?.getString("countryRegion") ?: ""
            val countryCurrency = backStackEntry.arguments?.getString("countryCurrency") ?: ""

            // Display the country detail screen
            MessageDetailScreen(
                MessageResponse(
                    name = Name(countryName),
                    cca2 = countryCca2,
                    capital = listOf(countryCapital),
                    region = countryRegion,
                    currencies = mapOf("currency" to Currency(countryCurrency))
                ),
                innerPaddingValues = innerPaddingValues,
                navController = navController
            )
        }
    }
}

@Composable
fun CountryListScreen(
    messageRepository: MessageRepositoryImpl,
    innerPaddingValues: PaddingValues,
    navController: NavController
) {
    // Create a ViewModel for the screen using a factory for dependency injection
    val viewModel: MessageViewModel = viewModel(factory = MessageViewModelFactory(messageRepository))

    // Observe the list of countries from the ViewModel
    val countries by viewModel.messages.observeAsState(emptyList())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPaddingValues)
            .background(Color.White)
            .padding(16.dp)
    ) {
        Column {
            // Display a list of countries in a lazy column
            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Iterate over the list of countries and display each as a card
                items(countries) { country ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                // Navigate to the detail screen, passing arguments in the route
                                navController.navigate(
                                    "countryDetail/${country.name.common}/${country.cca2}/${country.capital.firstOrNull() ?: ""}/${country.region}/${country.currencies.values.firstOrNull()?.name ?: ""}"
                                )
                            },
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD))
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Display the country's flag
                            Image(
                                painter = rememberAsyncImagePainter("https://flagcdn.com/w320/${country.cca2.lowercase()}.png"),
                                contentDescription = "Flag of ${country.name.common}",
                                modifier = Modifier.size(32.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            // Display the country's name
                            Text(
                                text = country.name.common,
                                fontSize = 16.sp,
                                color = Color(0xFF0D47A1)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Button to load the next country (fetches new data from the ViewModel)
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
