package dev.rhcehd123.simpleorder.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp

val ButtonHeight = 60.dp
val ButtonShape = RoundedCornerShape(15.dp)

@Composable
fun SimpleOrderButton(
    modifier: Modifier = Modifier,
    text: String = "",
    isMaxWidth: Boolean = false,
    onClick: () -> Unit,
) {
    Button(
        modifier = if(isMaxWidth) {
            modifier
                .height(ButtonHeight)
                .fillMaxWidth()
        } else {
            modifier
                .height(ButtonHeight)
        },
        shape = ButtonShape,
        onClick = onClick,
    ) {
        Text(
            text = text,
            fontSize = TextUnit(20f, TextUnitType.Sp),
        )
    }
}

@Preview
@Composable
fun SimpleOrderButtonPreview() {
    SimpleOrderButton(
        text = "BUTTON",
        onClick = {}
    )
}
