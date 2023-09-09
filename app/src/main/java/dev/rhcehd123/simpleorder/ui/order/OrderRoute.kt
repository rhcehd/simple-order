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
        onBack = { viewModel.onBack() },
        onComplete = { viewModel.onCompleteOrder() },
        itemList = dummyData,
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
    onBack: () -> Unit,
    onComplete: () -> Unit,
    itemList: List<OrderItem>,
) {
    when(getOrderScreenType(uiState)) {
        OrderScreenType.OrderList -> {
            OrderListScreen(
                onClickMenuItem = onClickMenuItem,
                onBack = navigateToIntro,
                itemList = itemList,
            )
        }
        OrderScreenType.OrderDetail -> {
            check(uiState is OrderUiState.HasSelectedItem)
            OrderDetailScreen (
                selectedItem = uiState.selectedItem,
                selectedOptions = uiState.selectedOptions,
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