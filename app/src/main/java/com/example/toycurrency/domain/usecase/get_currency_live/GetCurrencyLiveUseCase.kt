package com.example.toycurrency.domain.usecase.get_currency_live

import com.example.toycurrency.domain.model.CurrencyItem
import com.example.toycurrency.domain.model.asCurrencyItem
import com.example.toycurrency.domain.repository.CurrencyRepository
import com.example.toycurrency.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
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
                val currencyItem = currencyRepository.getLive(source, nation)
                    .asCurrencyItem(recipientCountry = nation, money = money.toInt())
                emit(Result.Success(currencyItem))
            } else {
                emit(Result.Error("The remittance amount is not correct"))
            }

        } catch (e: HttpException) {
            emit(Result.Error(e.localizedMessage ?: "An unexpected error occured"))
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