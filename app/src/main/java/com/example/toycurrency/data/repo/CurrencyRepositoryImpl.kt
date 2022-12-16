package com.example.toycurrency.data.repo

import com.example.toycurrency.service.CurrencyService
import com.example.toycurrency.service.reponse.CurrencyResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(private val currencyService: CurrencyService) :
    CurrencyRepository {
    override fun getLive(source: String, currencies: String): Flow<CurrencyResponse> =
        flow { emit(currencyService.getLive(source = source, currencies = currencies)) }
}