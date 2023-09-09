package dev.rhcehd123.simpleorder.data.repository

import dev.rhcehd123.simpleorder.data.model.Order
import dev.rhcehd123.simpleorder.data.model.OrderItem

interface OrderRepository {
    fun getAvailableOptions(): Result<Map<String, List<String>>>
    fun getOrderItems(): Result<List<OrderItem>>
    fun requestOrder(order: Order): Result<Order>
}

class FakeOrderRepository: OrderRepository {

    override fun getAvailableOptions(): Result<Map<String, List<String>>> {
        return Result.success(options)
    }

    override fun getOrderItems(): Result<List<OrderItem>> {
        return Result.success(orderItems)
    }

    override fun requestOrder(order: Order): Result<Order> {
        return Result.success(order)
    }
}