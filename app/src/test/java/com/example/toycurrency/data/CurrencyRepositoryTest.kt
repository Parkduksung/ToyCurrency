package com.example.toycurrency.data

import com.example.toycurrency.data.repo.CurrencyRepository
import com.example.toycurrency.data.repo.CurrencyRepositoryImpl
import com.example.toycurrency.service.CurrencyService
import com.example.toycurrency.util.MockUtil
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.collectLatest
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
        whenever(currencyService.getLive(source = "USD", currencies = "KRW,JPY,PHP")).thenReturn(
            MockUtil.mockCurrencyResponse()
        )

        //when
        currencyRepository.getLive(source = "USD", currencies = "KRW,JPY,PHP").collectLatest {
            //then
            assertEquals(it, MockUtil.mockCurrencyResponse())
        }
    }
}
