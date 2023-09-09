package dev.rhcehd123.simpleorder.utils

import android.icu.text.DecimalFormat
import dev.rhcehd123.simpleorder.data.model.OrderItem

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

fun sortOrderItems(list: List<OrderItem>): Map<String, List<OrderItem>> {
    val categoryMap = HashMap<String, ArrayList<OrderItem>>()
    list.forEach {
        if(categoryMap.containsKey(it.category)) {
            categoryMap[it.category]?.add(it)
        } else {
            categoryMap[it.category] = arrayListOf(it)
        }
    }
    return categoryMap
}

fun Map<String, String>.toOptionString(): String {
    val buf = StringBuilder()
    values.forEachIndexed { index, option ->
        buf.append(option)
        if(index != values.size - 1) {
            buf.append("/")
        }
    }
    return buf.toString()
}

