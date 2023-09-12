package dev.rhcehd123.simpleorder.ui.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dev.rhcehd123.simpleorder.data.model.OrderItem
import dev.rhcehd123.simpleorder.data.repository.OrderRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed interface OrderUiState {

    data class NoSelectedItem(
        val orderItemList: List<OrderItem>,
    ): OrderUiState

    data class HasSelectedItem(
        val isOrderCompleted: Boolean,
        val selectedItem: OrderItem,
        val selectedOptions: Map<String, String>,
        val availableOptions: Map<String, List<String>>,
    ): OrderUiState
}

private data class OrderViewModelState(
    val orderItemList: List<OrderItem>? = null,
    val availableOptions: Map<String, List<String>>? = null,
    val selectedItem: OrderItem? = null,
    val selectedOptions: Map<String, String>? = null,
    val isOrderCompleted: Boolean = false
) {
    fun getDefaultOptions(orderItem: OrderItem): Map<String, String> {
        val defaultOptions = mutableMapOf<String, String>()
        orderItem.options.forEach { title ->
            defaultOptions[title] = availableOptions?.get(title)?.get(0) ?: ""
        }
        return defaultOptions.toMap()
    }
    fun toUiState() =
        if(selectedItem == null) {
            OrderUiState.NoSelectedItem(
                orderItemList = if(orderItemList.isNullOrEmpty()) {
                    listOf()
                } else {
                    orderItemList
                }
            )
        } else {
            OrderUiState.HasSelectedItem(
                isOrderCompleted = isOrderCompleted,
                selectedItem = selectedItem,
                selectedOptions = selectedOptions?.toMap() ?: mapOf(),
                availableOptions = availableOptions?.toMap() ?: mapOf(),
            )
        }
}

class OrderViewModel(
    val orderRepository: OrderRepository,
): ViewModel() {

    private val viewModelState = MutableStateFlow(
        OrderViewModelState()
    )
    init {
        viewModelScope.launch {
            val orderItemList = orderRepository.getOrderItems().getOrNull()
            val availableOptions = orderRepository.getAvailableOptions().getOrNull()
            viewModelState.update {
                it.copy(
                    orderItemList = orderItemList,
                    availableOptions = availableOptions,
                )
            }
        }
    }

    val uiState = viewModelState
        .map(OrderViewModelState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    fun onClickMenuItem(orderItem: OrderItem) {
        viewModelState.update {
            it.copy(
                selectedItem = orderItem,
                selectedOptions = it.getDefaultOptions(orderItem),
            )
        }
    }

    fun onCancelSelectedItem() {
        viewModelState.update {
            it.copy(
                selectedItem = null,
                selectedOptions = mapOf(),
            )
        }
    }

    fun onClickOrder() {
        // send order request
        if(true) { // order result successful

            viewModelState.update {
                it.copy(
                    isOrderCompleted = true,
                )
            }
        } else { // handle failure

        }
    }

    fun onSelectOption(title: String, option: String) {
        viewModelState.update {
            it.copy(
                selectedOptions = it.selectedOptions?.plus(title to option),
            )
        }
    }

    fun onCompleteOrder() {
        viewModelState.update {
            it.copy(
                selectedItem = null,
                selectedOptions = null,
                isOrderCompleted = false,
            )
        }
    }

    companion object {
        fun provideFactory(
            orderRepository: OrderRepository,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return OrderViewModel(orderRepository) as T
            }
        }
    }
}