package dev.rhcehd123.simpleorder.utils

import android.icu.text.DecimalFormat

private val priceFormat = DecimalFormat("#,### 원")

fun Int.toPriceString(): String {
    return if(this != 0) {
        try {
            priceFormat.format(this)
        } catch (e: IllegalArgumentException) {
            throw e
        }
    } else {
        "무료"
    }
}

fun Map<String, String>.toOptionString(): String {
    val buf = StringBuilder()
    values.forEachIndexed { index, option ->
        buf.append(option)
        if(index != values.size - 1) {
            buf.append(" / ")
        }
    }
    return buf.toString()
}

