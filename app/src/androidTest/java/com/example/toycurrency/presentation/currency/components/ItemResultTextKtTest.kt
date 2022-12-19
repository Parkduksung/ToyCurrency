package com.example.toycurrency.presentation.currency.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.example.toycurrency.base.BaseComposeTest
import org.junit.Test

class ItemResultTextKtTest : BaseComposeTest() {

    @Test
    fun initTest() {

        //given, when
        initUi { ItemResultText(content = "", result = "") }

        //then
        with(composeTestRule){
            onNodeWithTag("ItemResultTextRow").assertExists()
            onNodeWithTag("ContentText").assertExists()
            onNodeWithTag("ColonText").assertExists()
            onNodeWithTag("ResultText").assertExists()
        }
    }

    @Test
    fun showContentAndResultTest() {

        //given
        val mockContent = "TestContent"
        val mockResult = "TestResult"

        //when
        initUi { ItemResultText(content = mockContent, result = mockResult) }

        //then
        composeTestRule.onNodeWithText(mockContent).assertIsDisplayed()
        composeTestRule.onNodeWithText(mockResult).assertIsDisplayed()
    }

}