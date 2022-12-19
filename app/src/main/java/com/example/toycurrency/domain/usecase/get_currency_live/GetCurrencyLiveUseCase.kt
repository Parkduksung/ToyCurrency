package com.example.toycurrency.domain.usecase.get_currency_live

import com.example.toycurrency.domain.model.CurrencyItem
import com.example.toycurrency.domain.model.ResponseToItemConverter
import com.example.toycurrency.domain.repository.CurrencyRepository
import com.example.toycurrency.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetCurrencyLiveUseCase @Inject constructor(
    private val currencyRepository: CurrencyRepository
) {
    operator fun invoke(
        source: String,
        nation: String,
        money: String
    ): Flow<Result<CurrencyItem>> = flow {
        try {
            emit(Result.Loading())

            if (money.toIntOrNull() in IntRange(START_RANGE, END_RANGE)) {
                val currencyItem = ResponseToItemConverter.toCurrencyItem(
                    response = currencyRepository.getLive(
                        source,
                        nation
                    ), recipientCountry = nation, money = money.toInt()
                )
                emit(Result.Success(currencyItem))
            } else {
                emit(Result.Error("The remittance amount is not correct"))
            }
        } catch (e: IOException) {
            emit(Result.Error("Couldn't reach server. Check your internet connection."))
        } catch (e: Exception) {
            emit(Result.Error(e.localizedMessage ?: "An unexpected error occured"))
        }
    }

    companion object {
        private const val START_RANGE = 1
        private const val END_RANGE = 9999
    }

}