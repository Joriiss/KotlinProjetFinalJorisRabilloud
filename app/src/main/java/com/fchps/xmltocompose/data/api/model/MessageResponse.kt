package com.fchps.xmltocompose.data.api.model

import com.google.gson.annotations.SerializedName

// Data model representing the response for a country message
data class MessageResponse(
    // The name of the country (e.g., common name of the country)
    @SerializedName("name") val name: Name,

    // The country code (CCA2 - 2-letter code)
    @SerializedName("cca2") val cca2: String,

    // The capital(s) of the country (could be more than one, stored as a list)
    @SerializedName("capital") val capital: List<String>,

    // The region to which the country belongs
    @SerializedName("region") val region: String,

    // A map of currencies used in the country
    @SerializedName("currencies") val currencies: Map<String, Currency>
)

// Data model for the country's name, which includes the common name
data class Name(
    // The common name of the country (e.g., "United States")
    @SerializedName("common") val common: String
)

// Data model representing the details of a currency used in the country
data class Currency(
    // The name of the currency (e.g., "Dollar")
    @SerializedName("name") val name: String
)
