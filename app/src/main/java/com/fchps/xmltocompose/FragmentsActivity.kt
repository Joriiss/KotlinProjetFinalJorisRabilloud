package com.fchps.xmltocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import com.fchps.xmltocompose.composables.MessageScreen
import com.fchps.xmltocompose.data.api.MessageApi
import com.fchps.xmltocompose.data.remote.MessageRepositoryImpl
import com.fchps.xmltocompose.ui.theme.XmlToComposeTheme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.fchps.xmltocompose.data.api.RetroFitSingleton

class FragmentsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val retrofit = RetroFitSingleton.getInstance()
        val api = retrofit.create(MessageApi::class.java)
        val repository = MessageRepositoryImpl(api)

        setContent {
            XmlToComposeTheme {
                Scaffold { innerPadding ->
                    MessageScreen(
                        innerPaddingValues = innerPadding,
                        messageRepository = repository
                    )
                }
            }
        }
    }
}