package dev.rhcehd123.simpleorder.ui.intro

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import dev.rhcehd123.simpleorder.ui.components.SimpleOrderButton

@Composable
fun IntroScreen(
    modifier: Modifier = Modifier,
    onNext: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 80.dp, bottom = 28.dp)
            .padding(horizontal = 28.dp)
    ) {
        val introTextFontSize = TextUnit(30f, TextUnitType.Sp)
        Text(
            text = "반가워요",
            fontSize = introTextFontSize,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = "주문을 시작할게요",
            fontSize = introTextFontSize,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.weight(1f))
        SimpleOrderButton(
            text = "다음",
            isMaxWidth = true,
            onClick = onNext,
        )
    }
}

@Preview
@Composable
fun IntroScreenPreview() {
    IntroScreen()
}