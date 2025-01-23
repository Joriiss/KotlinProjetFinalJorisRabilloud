package com.fchps.xmltocompose.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fchps.xmltocompose.data.api.MessageApi
import com.fchps.xmltocompose.data.api.model.MessageResponse
import com.fchps.xmltocompose.repository.MessageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MessageRepositoryImpl(private val api: MessageApi) : MessageRepository {
    private val _messages = MutableLiveData<List<MessageResponse>>()
    override val messages: LiveData<List<MessageResponse>> get() = _messages

    private var currentIndex = 0
    private var allMessages: List<MessageResponse> = emptyList()

    override suspend fun fetchMessages() {
        withContext(Dispatchers.IO) {
            allMessages = api.getPosts()
            _messages.postValue(emptyList())
            currentIndex = 0
        }
    }

    override suspend fun addMessage() {
        withContext(Dispatchers.IO) {
            if (currentIndex < allMessages.size) {
                val currentMessages = _messages.value.orEmpty().toMutableList()
                currentMessages.add(allMessages[currentIndex])
                _messages.postValue(currentMessages)
                Log.d("MessageRepositoryImpl", "Added message at index $currentIndex: ${allMessages[currentIndex]}")
                currentIndex++
            } else {
                Log.d("MessageRepositoryImpl", "No more messages to add")
            }
        }
    }
}