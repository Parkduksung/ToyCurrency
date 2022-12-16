package com.example.toycurrency

import com.example.toycurrency.service.reponse.CurrencyResponse
import com.example.toycurrency.util.MockUtil
import com.example.toycurrency.util.mockData
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Test

class SerializableTest {

    @Test
    fun `serializableTest`() {

        val json = Json {
            prettyPrint = true
            ignoreUnknownKeys = true
        }

        //when
        val getResponse = json.decodeFromString<CurrencyResponse>(mockData)


        //then
        assertEquals(
            getResponse,
            MockUtil.mockCurrencyResponse()
        )
    }
}
