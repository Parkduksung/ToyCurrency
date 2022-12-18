package com.example.toycurrency.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.toycurrency.domain.usecase.get_currency_live.GetCurrencyLiveUseCase
import com.example.toycurrency.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(private val getCurrencyLiveUseCase: GetCurrencyLiveUseCase) :
    ViewModel() {

    private var selectNation = nationList[INIT_NATION_INDEX]

    private val _state = mutableStateOf(CurrencyViewState())
    val state: State<CurrencyViewState> get() = _state

    fun setNation(type: String) {
        selectNation = type
    }

    fun currencyExchange(money: String) {
        getCurrencyLiveUseCase(SOURCE, selectNation, money).onEach { result ->
            when (result) {
                is Result.Error -> {
                    _state.value =
                        CurrencyViewState(error = result.message.orEmpty())
                }
                is Result.Loading -> {
                    _state.value =
                        CurrencyViewState(isLoading = true)
                }
                is Result.Success -> {
                    _state.value =
                        CurrencyViewState(currencyItem = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    companion object {
        const val INIT_NATION_INDEX = 0
        val nationList = listOf("KRW", "JPY", "PHP")
        private const val SOURCE = "USD"
    }
}
