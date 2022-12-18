package com.example.toycurrency.di


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
object TestAppModule {

    @Provides
    @Singleton
    fun provideCurrencyService(converter: Converter.Factory): CurrencyService {
        return Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(converter)
            .build()
            .create(CurrencyService::class.java)
    }

    @Provides
    @Singleton
    fun provideSerializableConverterFactory(): Converter.Factory =
        Json {
            ignoreUnknownKeys = true
            prettyPrint = true
        }.asConverterFactory(MediaType.parse("application/json")!!)


    @Provides
    @Singleton
    fun provideCurrencyRepository(service: CurrencyService): CurrencyRepository {
        return CurrencyRepositoryImpl(service)
    }
}