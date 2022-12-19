package com.example.toycurrency.domain.usecase.get_currency_live

import com.example.toycurrency.domain.repository.CurrencyRepository
import com.example.toycurrency.util.MockUtil
import com.example.toycurrency.util.Result
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.IOException


class GetCurrencyLiveUseCaseTest {

    private lateinit var getCurrencyLiveUseCase: GetCurrencyLiveUseCase
    private val currencyRepository: CurrencyRepository = mock()

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        getCurrencyLiveUseCase = GetCurrencyLiveUseCase(currencyRepository)
    }


    @Test
    fun invokeSuccessTest() = runBlocking {

        whenever(
            currencyRepository.getLive(
                "USD",
                "KRW"
            )
        ).thenReturn(MockUtil.mockCurrencyResponse())


        getCurrencyLiveUseCase("USD", "KRW", "100").collectLatest { result ->

            when (result) {
                is Result.Loading -> {
                    assertEquals(result.data, null)
                    assertEquals(result.message, null)
                }

                is Result.Success -> {
                    assertEquals(result.message, null)
                    assertEquals(
                        result.data,
                        MockUtil.mockCurrencyItem()
                    )
                }
            }
        }

    }

    @Test
    fun invokeFailTest() = runBlocking {


        currencyRepository.stub {
            onBlocking { getLive("USD", "KRW") } doAnswer {
                throw IOException("Couldn't reach server. Check your internet connection.")
            }
        }

        //when
        getCurrencyLiveUseCase("USD", "KRW", "100").collectLatest { result ->

            //then
            when (result) {
                is Result.Error -> {
                    assertEquals(result.data, null)
                    assertEquals(
                        result.message,
                        "Couldn't reach server. Check your internet connection."
                    )
                }

                is Result.Loading -> {
                    assertEquals(result.data, null)
                    assertEquals(result.message, null)
                }

            }

        }

    }


    @Test
    fun moneyRangeOverTest() = runBlocking {
        getCurrencyLiveUseCase("USD", "KRW", "10000").collectLatest { result ->
            when (result) {
                is Result.Error -> {
                    assertEquals(result.data, null)
                    assertEquals(
                        result.message,
                        "The remittance amount is not correct"
                    )
                }
                is Result.Loading -> {
                    assertEquals(result.data, null)
                    assertEquals(result.message, null)
                }

            }
        }
    }

}