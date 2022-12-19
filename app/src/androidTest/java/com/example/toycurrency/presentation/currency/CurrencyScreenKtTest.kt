package com.example.toycurrency.presentation.currency

import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.test.*
import androidx.lifecycle.ViewModel
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.toycurrency.base.BaseComposeTest
import com.example.toycurrency.di.TestAppModule
import com.example.toycurrency.domain.repository.CurrencyRepository
import com.example.toycurrency.domain.usecase.get_currency_live.GetCurrencyLiveUseCase
import com.example.toycurrency.service.CurrencyService
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@UninstallModules(TestAppModule::class)
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class CurrencyScreenKtTest : BaseComposeTest() {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var currencyService: CurrencyService

    @Inject
    lateinit var currencyRepository: CurrencyRepository

    private lateinit var getCurrencyLiveUseCase: GetCurrencyLiveUseCase

    private lateinit var currencyViewModel: CurrencyViewModel

    @Before
    fun setUp() {
        hiltRule.inject()
        getCurrencyLiveUseCase = GetCurrencyLiveUseCase(currencyRepository)
        currencyViewModel = CurrencyViewModel(getCurrencyLiveUseCase)
    }

    @Test
    fun inputTextAndResultTest() {

        initUi { CurrencyScreen(currencyViewModel) }

        //given
        composeTestRule.onNodeWithTag("InputNumberTextField", useUnmergedTree = true)
            .performTextInput("100")

        //when
        composeTestRule.onNodeWithTag("InputNumberTextField", useUnmergedTree = true)
            .performImeAction()

        //if 3 second over, fail test
        composeTestRule.waitUntil(3000) {
            currencyViewModel.state.value.currencyItem != null
        }

        //then
        currencyViewModel.state.value.currencyItem?.let { item ->
            composeTestRule.onNodeWithText(item.exchangeRate).assertIsDisplayed()
            composeTestRule.onNodeWithText(item.timestamp).assertIsDisplayed()
            composeTestRule.onNodeWithText("수취금액은 ${item.recipientMoney} 입니다").assertIsDisplayed()
        }
    }

    @Test
    fun rangeFailTest() {

        initUi { CurrencyScreen(currencyViewModel) }

        composeTestRule.onNodeWithTag("InputNumberTextField", useUnmergedTree = true)
            .performTextInput("100000")
        composeTestRule.onNodeWithTag("InputNumberTextField", useUnmergedTree = true)
            .performImeAction()

        composeTestRule.waitUntil {
            currencyViewModel.state.value == CurrencyViewState(error = "The remittance amount is not correct")
        }

        composeTestRule.onNodeWithText("The remittance amount is not correct").assertIsDisplayed()
    }

}