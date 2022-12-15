package com.example.toycurrency.data

import com.example.toycurrency.util.MockCurrencyResponse
import com.example.toycurrency.util.MockUtil
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.http.GET
import retrofit2.http.Query


class CurrencyRepositoryImplTest {

    private lateinit var currencyRepository: CurrencyRepository
    private val currencyService: CurrencyService = mock()


    @Before
    fun setUp() {
        currencyRepository = CurrencyRepositoryImpl(currencyService)
    }

    @Test
    fun getCurrencyTest() = runBlocking {
        //given
        whenever(currencyService.getLive(source = "USD", currencies = "KRW,JPY,PHP")).thenReturn(MockUtil.mockCurrencyResponse())

        assertEquals(
            currencyRepository.getLive(source = "USD", currencies = "KRW,JPY,PHP"),
            MockUtil.mockCurrencyResponse()
        )
    }
}

class CurrencyRepositoryImpl(private val currencyService: CurrencyService) : CurrencyRepository {

    override suspend fun getLive(source: String, currencies: String): MockCurrencyResponse =
        currencyService.getLive(source, currencies)
}

interface CurrencyService {

    companion object {
        private const val URL_GET_LIVE = ""
    }

    @GET(URL_GET_LIVE)
    suspend fun getLive(
        @Query("source") source: String,
        @Query("currencies") currencies: String,
    ): MockCurrencyResponse
}

interface CurrencyRepository {
    suspend fun getLive(source: String, currencies: String): MockCurrencyResponse
}


