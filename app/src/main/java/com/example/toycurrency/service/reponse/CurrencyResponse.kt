package com.example.toycurrency.service.reponse

@kotlinx.serialization.Serializable
data class CurrencyResponse(
    val success: Boolean,
    val timestamp: Long,
    val source: String,
    val quotes: Map<String, Double>
)
