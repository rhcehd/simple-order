package dev.rhcehd123.simpleorder.data

import dev.rhcehd123.simpleorder.data.repository.OrderRepository

interface AppContainer {
    val orderRepository: OrderRepository
}

class AppContainerImpl(
    override val orderRepository: OrderRepository
): AppContainer