package com.example.toycurrency.service.reponse

@kotlinx.serialization.Serializable
data class CurrencyResponse(
    val success: Boolean,
    val timestamp: Long? = null,
    val source: String? = null,
    val quotes: Map<String, Double>? = null
)
