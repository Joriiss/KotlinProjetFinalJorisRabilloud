package com.fchps.xmltocompose.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.foundation.background
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fchps.xmltocompose.viewmodel.MainViewModelBis
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import android.util.Patterns
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.ui.res.painterResource
import com.fchps.xmltocompose.R

@Composable
fun MainScreen(
    viewModelBis: MainViewModelBis = viewModel(),
    innerPaddingValues: PaddingValues,
    onSubmit: () -> Unit,
    onGoToFragmentsActivity: () -> Unit
) {
    // Get the current values of email and password from the ViewModel
    val email by viewModelBis.email
    val password by viewModelBis.password
    val context = LocalContext.current

    // Main container for the screen
    Column(
        modifier = Modifier
            .padding(innerPaddingValues)
            .fillMaxSize()
            .background(Color.White)
            .padding(start = 16.dp, end = 16.dp, top = 44.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Display the app logo
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App Logo",
            modifier = Modifier.size(128.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Display the title
        Text(
            text = "World Countries List",
            fontSize = 32.sp,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Email input field
        OutlinedTextField(
            value = email,
            onValueChange = { newMail ->
                viewModelBis.onMailChanged(newMail)
            },
            label = { Text("Saisissez votre adresse mail") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email
            ),
            textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password input field
        OutlinedTextField(
            value = password,
            onValueChange = { newPassword ->
                viewModelBis.onPasswordChanged(newPassword)
            },
            label = { Text("Saisissez votre mot de passe") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password
            ),
            textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Login button
        Button(
            onClick = {
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(context, "Fields cannot be empty", Toast.LENGTH_SHORT).show()
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(context, "Invalid email address", Toast.LENGTH_SHORT).show()
                } else {
                    onGoToFragmentsActivity()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text("Login")
        }
    }
}