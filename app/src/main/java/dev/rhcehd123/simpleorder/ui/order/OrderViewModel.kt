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

sealed interface OrderUiState {

    data class NoSelectedItem(
        val property: Int
    ): OrderUiState

    data class HasSelectedItem(
        val isOrderCompleted: Boolean,
        val selectedItem: OrderItem,
        val selectedOptions: Map<String, String>,
    ): OrderUiState
}

private data class OrderViewModelState(
    val selectedItem: OrderItem? = null,
    val selectedOptions: Map<String, String> = mapOf(),
    val isOrderCompleted: Boolean = false
) {
    fun toUiState() =
        if(selectedItem == null) {
            OrderUiState.NoSelectedItem(0)
        } else {
            OrderUiState.HasSelectedItem(
                isOrderCompleted = isOrderCompleted,
                selectedItem = selectedItem,
                selectedOptions = selectedOptions.toMap()
            )
        }
}

class OrderViewModel(
    val orderRepository: OrderRepository,
): ViewModel() {

    private val viewModelState = MutableStateFlow(
        OrderViewModelState()
    )
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
                selectedOptions = it.selectedOptions.plus(title to option),
            )
        }
    }

    fun onBack() {

    }

    fun onCompleteOrder() {
        viewModelState.update {
            OrderViewModelState()
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