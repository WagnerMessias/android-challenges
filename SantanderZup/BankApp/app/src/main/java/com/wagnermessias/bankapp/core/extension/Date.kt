package com.wagnermessias.bankapp.core.extension

import java.text.SimpleDateFormat
import java.util.*

fun Date.format(pattern: String = "dd/MM/yyyy"): String {
    val formatter = SimpleDateFormat(pattern, Locale.getDefault())
    return formatter.format(this)
}