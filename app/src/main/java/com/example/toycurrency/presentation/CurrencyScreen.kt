package com.example.toycurrency.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.toycurrency.presentation.components.ListPicker
import com.example.toycurrency.presentation.CurrencyViewModel.Companion.INIT_NATION_INDEX
import com.example.toycurrency.presentation.CurrencyViewModel.Companion.nationList
import com.example.toycurrency.presentation.components.InputRemittance
import com.example.toycurrency.presentation.components.ItemResultText

@Composable
fun CurrencyScreen(viewModel: CurrencyViewModel = hiltViewModel()) {

    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .semantics { testTag = "progress" })
        }

        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }

        Column(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .weight(3f)
            ) {
                Text(
                    text = "환율 계산",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 16.dp),
                    fontSize = 40.sp,
                    textAlign = TextAlign.Center
                )
                ItemResultText("송금국가", state.currencyItem?.remittanceCountry.orEmpty())
                ItemResultText("수취국가", state.currencyItem?.recipientCountry.orEmpty())
                ItemResultText("환율", state.currencyItem?.exchangeRate.orEmpty())
                ItemResultText("조회시간", state.currencyItem?.timestamp.orEmpty())

                InputRemittance("USD", onDone = viewModel::currencyExchange)

                Text(
                    text = "수취금액은 ${state.currencyItem?.recipientMoney.orEmpty()} 입니다",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp, top = 16.dp, bottom = 16.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            ListPicker(
                initValue = nationList[INIT_NATION_INDEX],
                nationList = nationList,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                onValue = viewModel::setNation
            )
        }
    }
}
