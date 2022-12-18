package com.example.toycurrency.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ItemResultText(content: String, result: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(text = content, modifier = Modifier.weight(1f), textAlign = TextAlign.End)
        Text(text = ":", modifier = Modifier.weight(0.5f), textAlign = TextAlign.Center)
        Text(text = result, modifier = Modifier.weight(3f), textAlign = TextAlign.Start)
    }
}
