package dev.rhcehd123.simpleorder.data.repository

import dev.rhcehd123.simpleorder.data.model.OrderItem

val orderItems = listOf(
    OrderItem("사과", "과일", 1000, listOf("A", "B", "A optional")),
    OrderItem("배", "과일", 2000, listOf("A")),
    OrderItem("참외", "과일", 3000, listOf("B")),
    OrderItem("수박", "과일", 4000, listOf()),
    OrderItem("토마토", "채소", 4000, listOf("A", "B", "A optional")),
)

val options = mapOf(
    "A" to listOf("TRUE", "FALSE"),
    "B" to listOf("O", "X"),
    "A optional" to listOf("1", "2", "3")
)