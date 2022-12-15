package com.example.toycurrency

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Test

class SerializableTest {

    @Test
    fun `serializableTest`() {

        //given
        val mockResponse = """
            {
              "success": true,
              "timestamp": 1671105723,
              "source": "USD",
              "quotes": {
                "USDKRW": 1310.749618,
                "USDJPY": 136.5695,
                "USDPHP": 55.806502
              }
            }
        """.trimIndent()

        val json = Json {
            prettyPrint = true
            ignoreUnknownKeys = true
        }


        //when
        val getResponse = json.decodeFromString<CurrencyResponse>(mockResponse)



        //then
        assertEquals(
            getResponse,
            CurrencyResponse(
                success = true,
                timestamp = 1671105723,
                source = "USD",
                quotes = mapOf("USDKRW" to 1310.749618, "USDJPY" to 136.5695, "USDPHP" to 55.806502)
            )
        )
    }
}

@Serializable
data class CurrencyResponse(
    val success: Boolean,
    val timestamp: Long,
    val source: String,
    val quotes: Map<String, Double>
)