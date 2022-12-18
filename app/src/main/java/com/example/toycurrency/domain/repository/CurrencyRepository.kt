package com.example.toycurrency.domain.repository

import com.example.toycurrency.service.reponse.CurrencyResponse

interface CurrencyRepository {
    suspend fun getLive(source: String, currencies: String): CurrencyResponse
}