package com.example.toycurrency.data.repo

import com.example.toycurrency.service.reponse.CurrencyResponse
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {
    fun getLive(source: String, currencies: String): Flow<CurrencyResponse>
}