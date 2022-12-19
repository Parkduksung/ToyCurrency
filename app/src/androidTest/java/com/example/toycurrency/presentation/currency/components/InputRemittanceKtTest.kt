package com.example.toycurrency.presentation.currency.components

import androidx.compose.ui.test.*
import com.example.toycurrency.base.BaseComposeTest
import org.junit.Test

class InputRemittanceKtTest : BaseComposeTest() {


    @Test
    fun initTest() {
        //given, when
        initUi { InputRemittance(source = "") {} }


        //then
        with(composeTestRule) {
            onNodeWithTag("ItemRemittanceRow").assertExists()
            onNodeWithTag("InputNumberTextField").assertExists()
            onNodeWithTag("InputNumberHintText", useUnmergedTree = true).assertExists()
        }
    }

    @Test
    fun showSourceTest() {

        //given
        initUi { InputRemittance(source = "USD") {} }

        //when, then
        composeTestRule.onNodeWithText("송금액(USD)을 입력해주세요.").assertIsDisplayed()

    }

    @Test
    fun showInputTest() {
        //given
        initUi { InputRemittance(source = "USD") {} }

        //when
        composeTestRule.onNodeWithTag("InputNumberTextField").performTextInput("1000")

        //then
        composeTestRule.onNodeWithTag("InputNumberTextField").assertTextContains("1000")

    }
}
