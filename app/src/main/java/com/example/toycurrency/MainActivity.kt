package com.example.toycurrency

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.chargemap.compose.numberpicker.ListItemPicker
import com.example.toycurrency.ui.theme.ToyCurrencyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToyCurrencyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ListPicker(listOf("KSW", "JPY", "PHP"))
                }
            }
        }
    }
}

@Composable
fun ListPicker(nation : List<String>) {

    var state by remember { mutableStateOf(nation[0]) }

    ListItemPicker(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        label = { it },
        value = state,
        onValueChange = {
            state = it
        }, list = nation,
        dividersColor = MaterialTheme.colors.primary,
        textStyle = TextStyle(fontSize = 20.sp)
    )
}