package com.example.toycurrency.domain.model

import com.example.toycurrency.ext.convertTimeString
import com.example.toycurrency.service.reponse.CurrencyResponse
import java.text.DecimalFormat

data class CurrencyItem(
    val remittanceCountry: String,
    val recipientCountry: String,
    val exchangeRate: String,
    val timestamp: String,
    val recipientMoney: String
)


object ResponseToItemConverter {
    fun toCurrencyItem(
        response: CurrencyResponse,
        recipientCountry: String,
        money: Int
    ): CurrencyItem {
        val rate = response.quotes?.get(response.source + recipientCountry)

        return CurrencyItem(
            remittanceCountry = response.source.orEmpty(),
            recipientCountry = recipientCountry,
            exchangeRate = DecimalFormat("#,###.00").format(rate) + " $recipientCountry/${response.source}",
            timestamp = response.timestamp.convertTimeString("yyyy-MM-dd HH:mm"),
            recipientMoney = DecimalFormat("#,###.00").format(rate?.times(money)) + recipientCountry
        )
    }
}
