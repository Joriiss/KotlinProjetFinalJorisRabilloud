package com.fchps.xmltocompose.data.api

import com.fchps.xmltocompose.data.api.model.MessageResponse
import retrofit2.http.GET

interface MessageApi{
    @GET("/posts")
    suspend fun getPosts(): List<MessageResponse>
}