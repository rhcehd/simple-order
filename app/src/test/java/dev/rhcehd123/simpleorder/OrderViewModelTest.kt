package dev.rhcehd123.simpleorder

import dev.rhcehd123.simpleorder.data.repository.OrderRepository
import dev.rhcehd123.simpleorder.data.testOptions
import dev.rhcehd123.simpleorder.data.testOrderItems
import dev.rhcehd123.simpleorder.ui.order.OrderUiState
import dev.rhcehd123.simpleorder.ui.order.OrderViewModel
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk

class OrderViewModelTest: BehaviorSpec() {
    init {
        val orderRepository = mockk<OrderRepository>()
        coEvery { orderRepository.getOrderItems().getOrNull() } returns testOrderItems
        coEvery { orderRepository.getAvailableOptions().getOrNull() } returns testOptions
        val viewModel = OrderViewModel(orderRepository)

        Given("주문 리스트 진입 후") {
            When("주문 아이템을 선택 하지 않았을 때") {
                Then("주문 리스트의 수가 기대 값과 일치 한다") {
                    val currentOrderUiState = viewModel.uiState.value as OrderUiState.NoSelectedItem
                    currentOrderUiState.orderItemList.size shouldBe testOrderItems.size
                }
            }
            When("주문 아이템을 선택 했을 때") {
                Then("hello") {
                    0 shouldBe 0
                }
            }
        }
    }
}