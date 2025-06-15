package com.example.jastip.utils

import java.text.NumberFormat
import java.util.Locale

fun formatDoubleKeRupiah(value: Double, locale: Locale = Locale("in", "ID")): String {
    val formatter = NumberFormat.getNumberInstance(locale).apply {
        maximumFractionDigits = 0 // hilangkan koma/desimal
    }
    return formatter.format(value)
}

