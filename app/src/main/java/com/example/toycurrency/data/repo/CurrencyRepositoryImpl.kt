package com.example.toycurrency.data.repo

import com.example.toycurrency.domain.repository.CurrencyRepository
import com.example.toycurrency.service.CurrencyService
import com.example.toycurrency.service.reponse.CurrencyResponse
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(private val currencyService: CurrencyService) :
    CurrencyRepository {
    override suspend fun getLive(source: String, currencies: String): CurrencyResponse =
        currencyService.getLive(source = source, currencies = currencies)
}