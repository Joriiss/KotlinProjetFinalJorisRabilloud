package com.fchps.xmltocompose.data.api

import com.fchps.xmltocompose.data.api.model.MessageResponse
import retrofit2.http.GET

// Interface defining the API endpoints for retrieving country data
interface MessageApi {
    // A GET request to fetch a list of countries with selected fields (name, cca2, capital, region, and currencies)
    @GET("v3.1/all?fields=name,cca2,capital,region,currencies")

    // Function that makes the GET request and returns a list of MessageResponse objects
    // The function is marked as `suspend` to indicate that it should be called from a coroutine or another suspend function
    suspend fun getCountries(): List<MessageResponse>
}
