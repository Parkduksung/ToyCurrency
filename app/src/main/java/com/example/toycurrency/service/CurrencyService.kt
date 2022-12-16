package com.example.toycurrency.service

import com.example.toycurrency.constant.Constants.CURRENCY_API_KEY
import com.example.toycurrency.constant.Constants.CURRENCY_GET_LIVE
import com.example.toycurrency.service.reponse.CurrencyResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CurrencyService {

    @GET(CURRENCY_GET_LIVE)
    suspend fun getLive(
        @Header("apikey") apikey: String = CURRENCY_API_KEY,
        @Query("source") source: String,
        @Query("currencies") currencies: String,
    ): CurrencyResponse

}