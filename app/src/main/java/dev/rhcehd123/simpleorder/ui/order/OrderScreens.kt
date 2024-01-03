package dev.rhcehd123.simpleorder.ui.order

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.rhcehd123.simpleorder.data.model.OrderItem
import dev.rhcehd123.simpleorder.data.repository.category
import dev.rhcehd123.simpleorder.data.repository.options
import dev.rhcehd123.simpleorder.data.repository.orderItems
import dev.rhcehd123.simpleorder.ui.components.SimpleOrderButton
import dev.rhcehd123.simpleorder.ui.components.SimpleOrderScaffold
import dev.rhcehd123.simpleorder.ui.components.SimpleOrderSelector
import dev.rhcehd123.simpleorder.utils.TextSize
import dev.rhcehd123.simpleorder.utils.toOptionString
import dev.rhcehd123.simpleorder.utils.toPriceString

@Composable
fun OrderListScreen(
    onClickMenuItem: (OrderItem) -> Unit,
    onBack: () -> Unit,
    itemList: List<OrderItem>,
    categoryList: List<String>,
) {
    SimpleOrderScaffold(
        onBack = onBack
    ) { modifier ->
        MenuList(
            modifier = modifier
                .padding(horizontal = 28.dp)
                .padding(top = 28.dp),
            onClickMenuItem = onClickMenuItem,
            itemList = itemList,
            categoryList = categoryList,
        )
    }
}

@Composable
fun OrderDetailScreen(
    selectedItem: OrderItem,
    selectedOptions: Map<String, String>,
    availableOptions: Map<String, List<String>>,
    onBack: () -> Unit,
    onOrder: () -> Unit,
    onSelectOption: (String, String) -> Unit,
) {
    SimpleOrderScaffold(
        onBack = onBack
    ) { modifier ->
        Column(
            modifier = modifier
                .padding(top = 28.dp, bottom = 28.dp)
                .padding(horizontal = 28.dp)
        ) {
            Column {
                Text(
                    text = selectedItem.name,
                    fontSize = TextSize(sp = 16f),
                )
                Text(
                    text = selectedItem.price.toPriceString(),
                    fontSize = TextSize(sp = 20f),
                    fontWeight = FontWeight.SemiBold,
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            selectedItem.options.forEach { title ->
                val selectedOption = selectedOptions[title] ?: availableOptions[title]?.get(0) ?: ""
                SimpleOrderSelector(
                    title = title,
                    options = availableOptions[title] ?: listOf(),
                    selectedOption = selectedOption,
                    onSelectOption = onSelectOption,
                )
            }
            Spacer(
                modifier = Modifier.weight(1f)
            )
            SimpleOrderButton(
                text = "주문하기",
                isMaxWidth = true,
                onClick = onOrder,
            )
        }
    }
}

@Composable
fun OrderSuccessScreen(
    selectedItem: OrderItem,
    selectedOptions: Map<String, String>,
    onComplete: () -> Unit,
) {
    SimpleOrderScaffold(
        onBack = onComplete
    ) { modifier ->
        Column(
            modifier = modifier
                .padding(top = 28.dp, bottom = 28.dp)
                .padding(horizontal = 28.dp)
        ) {
            Text(
                text = "주문 완료!",
                fontSize = TextSize(sp = 32f),
                fontWeight = FontWeight.Bold,
            )
            Spacer(
                modifier = Modifier.height(48.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = selectedItem.name,
                    )
                    Text(
                        text = selectedOptions.toOptionString(),
                    )
                }
                Text(
                    modifier = Modifier
                        .align(
                            alignment = Alignment.CenterVertically
                        ),
                    text = selectedItem.price.toPriceString()
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            SimpleOrderButton(
                text = "닫기",
                isMaxWidth = true,
                onClick = onComplete,
            )
        }
    }
}

@Composable
fun MenuList(
    modifier: Modifier = Modifier,
    onClickMenuItem: (OrderItem) -> Unit,
    itemList: List<OrderItem>,
    categoryList: List<String>
) {
    LazyColumn(
        modifier = modifier
            .padding(bottom = 12.dp)
    ) {
        categoryList.forEach { category ->
            item {
                CategoryTitle(category)
            }
            val categoryItem = itemList.filter { it.category == category }
            items(categoryItem) {
                MenuItem(
                    orderItem = it,
                    onClick = onClickMenuItem,
                )
                Divider()
            }
            item {
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}

@Composable
fun CategoryTitle(
    title: String = "",
) {
    Text(
        modifier = Modifier,
        text = title,
        fontWeight = FontWeight.ExtraBold,
    )
}

@Composable
fun MenuItem(
    orderItem: OrderItem,
    onClick: (OrderItem) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .clickable { onClick(orderItem) }
            .padding(vertical = 16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = orderItem.name,
            fontWeight = FontWeight.Bold,
        )
        Text(text = orderItem.price.toPriceString())
    }
}

@Preview()
@Composable
fun OrderListScreenPreview() {
    OrderListScreen(
        onClickMenuItem = {},
        onBack = {},
        itemList = orderItems,
        categoryList = category
    )
}

@Preview()
@Composable
fun OrderDetailScreenPreview() {
    OrderDetailScreen(
        selectedItem = orderItems[0],
        selectedOptions = mapOf(),
        availableOptions = options,
        onBack = {},
        onOrder = {},
        onSelectOption = {_,_->}
    )
}

@Preview()
@Composable
fun OrderSuccessScreenPreview() {
    OrderSuccessScreen(
        selectedItem = orderItems[0],
        selectedOptions = mapOf(
            "A" to "TRUE",
            "B" to "O",
            "A optional" to "1"
        ),
        onComplete = {},
    )
}