package com.example.toycurrency.presentation

import com.example.toycurrency.domain.model.CurrencyItem
import com.example.toycurrency.domain.model.ResponseToItemConverter
import com.example.toycurrency.domain.usecase.get_currency_live.GetCurrencyLiveUseCase
import com.example.toycurrency.util.MockUtil
import com.example.toycurrency.util.Result
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


@ExperimentalCoroutinesApi
class CurrencyViewModelTest {

    private lateinit var currencyViewModel: CurrencyViewModel
    private val getCurrencyLiveUseCase: GetCurrencyLiveUseCase = mock()

    private val dispatcher = UnconfinedTestDispatcher()

    private val responseToItemConverter: ResponseToItemConverter = mock()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)

    }

    @Test
    fun getCurrencyLoadingTest() = runTest {

        //given
        whenever(getCurrencyLiveUseCase("USD", "KRW", "100")).thenReturn(object :
            Flow<Result<CurrencyItem>> {
            override suspend fun collect(collector: FlowCollector<Result<CurrencyItem>>) {
                collector.emit(Result.Loading())
            }
        })

        //when
        currencyViewModel = CurrencyViewModel(getCurrencyLiveUseCase)
        currencyViewModel.setNation("KRW")
        currencyViewModel.currencyExchange("100")

        //then
        assertEquals(currencyViewModel.state.value.isLoading, true)
        assertEquals(currencyViewModel.state.value.currencyItem, null)
        assertEquals(currencyViewModel.state.value.error, "")
    }

    @Test
    fun getCurrencySuccessTest() = runTest {

        //given
        whenever(
            responseToItemConverter.toCurrencyItem(
                MockUtil.mockCurrencyResponse(),
                "KRW",
                100
            )
        ).thenReturn(MockUtil.mockCurrencyItem())

        whenever(getCurrencyLiveUseCase("USD", "KRW", "100")).thenReturn(object :
            Flow<Result<CurrencyItem>> {
            override suspend fun collect(collector: FlowCollector<Result<CurrencyItem>>) {
                collector.emit(
                    Result.Success(
                        responseToItemConverter.toCurrencyItem(
                            MockUtil.mockCurrencyResponse(),
                            "KRW",
                            100
                        )
                    )
                )
            }
        })

        //when
        currencyViewModel = CurrencyViewModel(getCurrencyLiveUseCase)
        currencyViewModel.setNation("KRW")
        currencyViewModel.currencyExchange("100")

        //then
        assertEquals(currencyViewModel.state.value.isLoading, false)
        assertEquals(
            currencyViewModel.state.value.currencyItem,
            MockUtil.mockCurrencyItem()
        )
        assertEquals(currencyViewModel.state.value.error, "")
    }

    @Test
    fun getCurrencyFailTest() = runTest {

        //given
        whenever(getCurrencyLiveUseCase("USD", "KRW", "100")).thenReturn(object :
            Flow<Result<CurrencyItem>> {
            override suspend fun collect(collector: FlowCollector<Result<CurrencyItem>>) {
                collector.emit(Result.Error(message = "Unknown Error"))
            }
        })

        //when
        currencyViewModel = CurrencyViewModel(getCurrencyLiveUseCase)
        currencyViewModel.setNation("KRW")
        currencyViewModel.currencyExchange("100")


        //then
        assertEquals(currencyViewModel.state.value.isLoading, false)
        assertEquals(currencyViewModel.state.value.currencyItem, null)
        assertEquals(currencyViewModel.state.value.error, "Unknown Error")
    }
}

