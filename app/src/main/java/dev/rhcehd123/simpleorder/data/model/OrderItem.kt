package dev.rhcehd123.simpleorder.data.model

data class OrderItem(
    val name: String,
    val category: String,
    val price: Int,
    val options: List<String>,
) {
    companion object {
        fun empty(): OrderItem = OrderItem("", "", 0, listOf())
    }
}