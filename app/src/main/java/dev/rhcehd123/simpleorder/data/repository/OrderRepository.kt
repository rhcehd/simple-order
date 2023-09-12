package dev.rhcehd123.simpleorder.data.repository

import dev.rhcehd123.simpleorder.data.model.Order
import dev.rhcehd123.simpleorder.data.model.OrderItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface OrderRepository {
    suspend fun getAvailableOptions(): Result<Map<String, List<String>>>
    suspend fun getOrderItems(): Result<List<OrderItem>>
    suspend fun requestOrder(order: Order): Result<Order>
}

class FakeOrderRepository: OrderRepository {

    override suspend fun getAvailableOptions(): Result<Map<String, List<String>>> {
        return withContext(Dispatchers.IO) {
            Result.success(options)
        }
    }

    override suspend fun getOrderItems(): Result<List<OrderItem>> {
        return withContext(Dispatchers.IO) {
            Result.success(orderItems)
        }
    }

    override suspend fun requestOrder(order: Order): Result<Order> {
        return withContext(Dispatchers.IO) {
            Result.success(order)
        }
    }
}