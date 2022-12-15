package com.example.toycurrency.util

import kotlinx.serialization.Serializable

object MockUtil {

    fun mockCurrencyResponse() = MockCurrencyResponse(
        success = true,
        timestamp = 1671105723,
        source = "USD",
        quotes = mapOf("USDKRW" to 1310.749618, "USDJPY" to 136.5695, "USDPHP" to 55.806502)
    )

}

val mockData =
    """
            {
              "success": true,
              "timestamp": 1671105723,
              "source": "USD",
              "quotes": {
                "USDKRW": 1310.749618,
                "USDJPY": 136.5695,
                "USDPHP": 55.806502
              }
            }
        """.trimIndent()


@Serializable
data class MockCurrencyResponse(
    val success: Boolean,
    val timestamp: Long,
    val source: String,
    val quotes: Map<String, Double>
)