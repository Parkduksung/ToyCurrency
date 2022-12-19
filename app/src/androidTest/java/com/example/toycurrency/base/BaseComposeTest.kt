package com.example.toycurrency.base

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import org.junit.Rule

abstract class BaseComposeTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    protected fun initUi(block: @Composable () -> Unit) {
        composeTestRule.setContent(block)
    }

    protected fun printLog(tag: String) {
        composeTestRule.onRoot().printToLog(tag = tag)
    }
}