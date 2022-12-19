package com.example.toycurrency.presentation.currency.components

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.chargemap.compose.numberpicker.ListItemPicker


@Composable
fun ListPicker(
    initValue: String,
    nationList: List<String>,
    modifier: Modifier = Modifier,
    onValue: (String) -> Unit
) {

    var selectValue by remember { mutableStateOf(initValue) }

    ListItemPicker(
        modifier = modifier,
        label = { it },
        value = selectValue,
        onValueChange = {
            selectValue = it
            onValue(selectValue)
        }, list = nationList,
        dividersColor = MaterialTheme.colors.primary,
        textStyle = TextStyle(fontSize = 20.sp)
    )
}