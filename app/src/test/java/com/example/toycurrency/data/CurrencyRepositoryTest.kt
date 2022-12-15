package com.example.toycurrency.data

import com.example.toycurrency.util.MockUtil
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


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
        whenever(currencyService.getLive(source = "USD", currencies = "KRW,JPY,PHP"))

        assertEquals(
            currencyRepository.getLive(source = "USD", currencies = "KRW,JPY,PHP"),
            MockUtil.mockCurrencyResponse()
        )
    }
}