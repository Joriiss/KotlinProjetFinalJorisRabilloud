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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fchps.xmltocompose.viewmodel.MainViewModelBis

@Composable
fun MainScreen(
    viewModelBis: MainViewModelBis = viewModel(),
    innerPaddingValues: PaddingValues,
    onSubmit: () -> Unit,
    onGoToFragmentsActivity: () -> Unit
) {
    val email by viewModelBis.email
    val password by viewModelBis.password

    Column(
        modifier = Modifier
            .padding(innerPaddingValues)
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 44.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bienvenue sur ma super application Android",
            fontSize = 32.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { newMail ->
                // Appeler viewModel pour le changement de mail
                viewModelBis.onMailChanged(newMail)
            },
            label = { Text("Saisissez votre adresse mail") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { newPassword ->
                // Appeler viewModel pour le changement de password
                viewModelBis.onPasswordChanged(newPassword)
            },
            label = { Text("Saisissez votre mot de passe") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onSubmit,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text("Go to next")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onGoToFragmentsActivity,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text("Go to FragmentsActivity")
        }
    }
}
