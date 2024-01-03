package dev.rhcehd123.simpleorder.ui.order

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.rhcehd123.simpleorder.data.model.OrderItem

@Composable
fun OrderRoute(
    viewModel: OrderViewModel = viewModel(),
    navigateToIntro: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    OrderRoute(
        uiState = uiState,
        navigateToIntro = navigateToIntro,
        onClickMenuItem = { item: OrderItem -> viewModel.onClickMenuItem(item) },
        onClickOrder = { viewModel.onClickOrder() },
        onSelectOption = { title, option -> viewModel.onSelectOption(title, option) },
        cancelSelectedItem = { viewModel.onCancelSelectedItem() },
        onComplete = { viewModel.onCompleteOrder() },
    )
}

@Composable
fun OrderRoute(
    uiState: OrderUiState,
    navigateToIntro: () -> Unit,
    onClickMenuItem: (OrderItem) -> Unit,
    onClickOrder: () -> Unit,
    onSelectOption: (String, String) -> Unit,
    cancelSelectedItem: () -> Unit,
    onComplete: () -> Unit,
) {
    when(getOrderScreenType(uiState)) {
        OrderScreenType.OrderList -> {
            check(uiState is OrderUiState.NoSelectedItem)
            OrderListScreen(
                onClickMenuItem = onClickMenuItem,
                onBack = navigateToIntro,
                itemList = uiState.orderItemList,
                categoryList = uiState.categoryList,
            )
        }
        OrderScreenType.OrderDetail -> {
            check(uiState is OrderUiState.HasSelectedItem)
            OrderDetailScreen (
                selectedItem = uiState.selectedItem,
                selectedOptions = uiState.selectedOptions,
                availableOptions = uiState.availableOptions,
                onBack = cancelSelectedItem,
                onOrder = onClickOrder,
                onSelectOption = onSelectOption
            )
            BackHandler { cancelSelectedItem() }
        }
        OrderScreenType.OrderSuccess -> {
            check(uiState is OrderUiState.HasSelectedItem)
            OrderSuccessScreen (
                selectedItem = uiState.selectedItem,
                selectedOptions = uiState.selectedOptions,
                onComplete = onComplete,
            )
            BackHandler { onComplete() }
        }
    }
}

private enum class OrderScreenType {
    OrderList,
    OrderDetail,
    OrderSuccess,
}

@Composable
private fun getOrderScreenType(uiState: OrderUiState): OrderScreenType {
    return if(uiState is OrderUiState.HasSelectedItem) {
        if(!uiState.isOrderCompleted) {
            OrderScreenType.OrderDetail
        } else {
            OrderScreenType.OrderSuccess
        }
    } else {
        OrderScreenType.OrderList
    }
}