package com.fchps.xmltocompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fchps.xmltocompose.repository.MessageRepository
import kotlinx.coroutines.launch

class MessageViewModel(private val messageRepository: MessageRepository) : ViewModel() {

    val messages = messageRepository.messages

    init {
        viewModelScope.launch {
            messageRepository.fetchMessages()
        }
    }

    fun addMessage() {
        viewModelScope.launch {
            messageRepository.addMessage()
        }
    }
}