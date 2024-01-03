package dev.rhcehd123.simpleorder.data.repository

import dev.rhcehd123.simpleorder.data.model.OrderItem

val orderItems = listOf(
    OrderItem(1, "에스프레소", "COFFEE", 4000, listOf("사이즈", "온도", "얼음 양", "컵")),
    OrderItem(2, "아메리카노", "COFFEE", 3000, listOf("사이즈", "온도", "얼음 양", "컵")),
    OrderItem(3, "카페라테", "COFFEE", 3000, listOf("사이즈", "온도", "얼음 양", "컵")),
    OrderItem(4, "콜드브루", "COFFEE", 4000, listOf("사이즈", "얼음 양", "컵")),

    OrderItem(1, "그린티", "TEA", 3000, listOf("사이즈", "온도", "얼음 양", "컵")),
    OrderItem(2, "밀크티", "TEA", 3000, listOf("사이즈", "온도", "얼음 양", "컵")),
    OrderItem(3, "아이스티", "TEA", 3000, listOf("사이즈", "얼음 양", "컵")),

    OrderItem(1, "허니 브레드", "Dessert", 6000, listOf()),
    OrderItem(2, "티라미수", "Dessert", 6000, listOf()),
)

val category = listOf(
    "COFFEE",
    "TEA",
    "Dessert",
)

val options = mapOf(
    "사이즈" to listOf("Tall", "Grande", "Venti"),
    "온도" to listOf("HOT", "ICED"),
    "얼음 양" to listOf("얼음 많이", "얼음 적게"),
    "컵" to listOf("매장 컵", "테이크아웃"),
)