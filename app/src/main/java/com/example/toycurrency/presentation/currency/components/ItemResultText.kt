package com.example.toycurrency.presentation.currency.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ItemResultText(content: String, result: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)
            .semantics { testTag = "ItemResultTextRow" },
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = content,
            modifier = Modifier
                .weight(1f)
                .semantics { testTag = "ContentText" },
            textAlign = TextAlign.End
        )
        Text(
            text = ":",
            modifier = Modifier
                .weight(0.5f)
                .semantics { testTag = "ColonText" },
            textAlign = TextAlign.Center
        )
        Text(
            text = result,
            modifier = Modifier
                .weight(3f)
                .semantics { testTag = "ResultText" },
            textAlign = TextAlign.Start
        )
    }
}
