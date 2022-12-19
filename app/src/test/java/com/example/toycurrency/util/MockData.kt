package com.example.toycurrency.util

import com.example.toycurrency.domain.model.CurrencyItem
import com.example.toycurrency.service.reponse.CurrencyResponse

object MockUtil {

    fun mockCurrencyResponse() = CurrencyResponse(
        success = true,
        timestamp = 1671105723,
        source = "USD",
        quotes = mapOf("USDKRW" to 1310.749618)
    )

    fun mockCurrencyItem() = CurrencyItem(
        remittanceCountry = "USD",
        recipientCountry = "KRW",
        exchangeRate = "1,310.75 KRW/USD",
        timestamp = "2022-12-19 09:20",
        recipientMoney = "131,074.96KRW"
    )

}

val mockData =
    """
            {
              "success": true,
              "timestamp": 1671105723,
              "source": "USD",
              "quotes": {
                "USDKRW": 1310.749618
              }
            }
        """.trimIndent()
