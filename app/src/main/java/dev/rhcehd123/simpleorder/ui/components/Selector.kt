package dev.rhcehd123.simpleorder.ui.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp

@Composable
fun SimpleOrderSelector(
    title: String = "",
    options: List<String>,
    selectedOption: String? = null,
    onSelectOption: (String, String) -> Unit,
) {
    Column(
        modifier = Modifier.padding(vertical = 16.dp)
    ){
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = title,
            fontSize = TextUnit(20f, TextUnitType.Sp),
            fontWeight = FontWeight.Medium,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            val buttonModifier = Modifier
                .weight(1f)
                .height(ButtonHeight)
            val spacerModifier = Modifier.padding(end = 12.dp)
            val a = MutableInteractionSource()

            options.forEachIndexed { index, option ->
                Button(
                    modifier = buttonModifier,
                    shape = ButtonShape,
                    colors = if(option == selectedOption) {
                        ButtonDefaults.buttonColors(
                            containerColor = Color.DarkGray,
                            contentColor = Color.White,
                        )
                    } else {
                        ButtonDefaults.buttonColors(
                            containerColor = Color.LightGray,
                            contentColor = Color.Black,
                        )
                    },
                    onClick = { onSelectOption(title, option) },
                ) {
                    Text(
                        text = option,
                        fontSize = TextUnit(15f, TextUnitType.Sp),
                    )
                }
                if(index != options.lastIndex) {
                    Spacer(modifier = spacerModifier)
                }
            }
        }
    }
}

@Preview
@Composable
fun SimpleOrderSelectorPreview() {
    SimpleOrderSelector(
        title = "SELECTOR",
        options = listOf("A", "B", "C"),
        selectedOption = "A",
        onSelectOption = { _, _ -> }
    )
}