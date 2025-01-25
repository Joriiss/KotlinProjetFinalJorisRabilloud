package com.fchps.xmltocompose.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Singleton object responsible for creating and providing the Retrofit instance.
object RetroFitSingleton {

    // The base URL for the REST API.
    private const val BASE_URL = "https://restcountries.com/"

    // This function returns a Retrofit instance that is configured with the base URL, 
    // an OkHttpClient, and a Gson converter to parse the JSON responses.
    fun getInstance(): Retrofit {

        // Creating an OkHttpClient instance that will handle the network requests.
        val client = OkHttpClient()

        // Setting up the HTTP logging interceptor to log the request and response bodies 
        // for debugging purposes. It logs the full request and response body.
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        // Building the client with the interceptor added to the chain.
        val clientBuilder: OkHttpClient.Builder = client.newBuilder().addInterceptor(interceptor)

        // Building and returning a Retrofit instance with the configured settings.
        return Retrofit.Builder()
            .baseUrl(BASE_URL)  // Setting the base URL for API requests.
            .client(clientBuilder.build())  // Adding the OkHttpClient to handle the HTTP requests.
            .addConverterFactory(GsonConverterFactory.create())  // Adding a converter to parse JSON responses.
            .build()  // Creating and returning the Retrofit instance.
    }
}
