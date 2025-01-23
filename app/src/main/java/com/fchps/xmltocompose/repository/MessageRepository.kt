package com.fchps.xmltocompose.repository

import androidx.lifecycle.LiveData
import com.fchps.xmltocompose.data.api.model.MessageResponse

interface MessageRepository {
    val messages: LiveData<List<MessageResponse>>
    suspend fun fetchMessages()
    suspend fun addMessage()
}