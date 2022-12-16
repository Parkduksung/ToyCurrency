package com.example.toycurrency.util

import com.example.toycurrency.service.reponse.CurrencyResponse

object MockUtil {

    fun mockCurrencyResponse() = CurrencyResponse(
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
