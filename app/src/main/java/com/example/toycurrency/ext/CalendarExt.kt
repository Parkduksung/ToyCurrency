package com.example.toycurrency.ext

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat


@SuppressLint("SimpleDateFormat")
fun Long?.convertTimeString(pattern : String) : String =
    SimpleDateFormat(pattern).format(this?.times(1000))
