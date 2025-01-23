package com.fchps.xmltocompose.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModelBis : ViewModel() {

    var email = mutableStateOf("")
        private set

    var password = mutableStateOf("")
        private set

    // Fonctions pour mettre à jour le mail et password
    fun onMailChanged(newMail: String) {
        email.value = newMail
    }

    fun onPasswordChanged(newPassword: String) {
        password.value = newPassword
    }
}