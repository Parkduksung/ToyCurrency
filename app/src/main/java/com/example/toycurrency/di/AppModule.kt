package com.example.toycurrency.di

import com.example.toycurrency.constant.Constants.BASE_CURRENCY_URL
import com.example.toycurrency.data.repo.CurrencyRepository
import com.example.toycurrency.data.repo.CurrencyRepositoryImpl
import com.example.toycurrency.service.CurrencyService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val json = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
    }

    @Provides
    @Singleton
    fun provideCurrencyService(converter: Converter.Factory): CurrencyService {
        return Retrofit.Builder()
            .baseUrl(BASE_CURRENCY_URL)
            .addConverterFactory(converter)
            .build()
            .create(CurrencyService::class.java)
    }

    @Provides
    @Singleton
    fun provideCurrencyRepository(service: CurrencyService): CurrencyRepository {
        return CurrencyRepositoryImpl(service)
    }

    @Provides
    @Singleton
    fun provideSerializableConverterFactory(): Converter.Factory =
        json.asConverterFactory(MediaType.parse("application/json")!!)
}