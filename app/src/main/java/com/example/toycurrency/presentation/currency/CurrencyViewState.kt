package com.example.toycurrency.presentation.currency

import com.example.toycurrency.domain.model.CurrencyItem

data class CurrencyViewState(
    val isLoading: Boolean = false,
    val currencyItem: CurrencyItem? = null,
    val error: String = ""
)