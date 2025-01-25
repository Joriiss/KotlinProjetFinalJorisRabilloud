package com.fchps.xmltocompose.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fchps.xmltocompose.data.api.MessageApi
import com.fchps.xmltocompose.data.api.model.MessageResponse
import com.fchps.xmltocompose.repository.MessageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

// Implementation of the MessageRepository interface that handles data operations
// (fetching data from the API and adding messages).
class MessageRepositoryImpl(private val api: MessageApi) : MessageRepository {

    // A LiveData object that holds the list of messages.
    private val _messages = MutableLiveData<List<MessageResponse>>()

    // Exposing the LiveData so other parts of the app can observe it.
    override val messages: LiveData<List<MessageResponse>> get() = _messages

    // Index to track the position in the list of messages.
    private var currentIndex = 0

    // List to hold all the fetched messages.
    private var allMessages: List<MessageResponse> = emptyList()

    // Function to fetch all messages from the API.
    // This runs on a background thread using the IO dispatcher.
    override suspend fun fetchMessages() {
        withContext(Dispatchers.IO) {
            // Fetch the countries data from the API and store it in the allMessages list.
            allMessages = api.getCountries()
            // Clear the current messages before adding new ones.
            _messages.postValue(emptyList())
            // Reset the currentIndex to start from the first message.
            currentIndex = 0
        }
    }

    // Function to add a message to the list of messages.
    // This also runs on a background thread.
    override suspend fun addMessage() {
        withContext(Dispatchers.IO) {
            // Check if there are still messages left to add.
            if (currentIndex < allMessages.size) {
                // Get the current list of messages, add the new message to it.
                val currentMessages = _messages.value.orEmpty().toMutableList()
                currentMessages.add(allMessages[currentIndex])
                // Update the LiveData with the new list of messages.
                _messages.postValue(currentMessages)
                // Log the added message for debugging purposes.
                Log.d("MessageRepositoryImpl", "Added message at index $currentIndex: ${allMessages[currentIndex]}")
                // Increment the currentIndex to point to the next message.
                currentIndex++
            } else {
                // Log a message when there are no more messages to add.
                Log.d("MessageRepositoryImpl", "No more messages to add")
            }
        }
    }
}
