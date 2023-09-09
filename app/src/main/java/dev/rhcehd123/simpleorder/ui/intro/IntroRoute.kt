package dev.rhcehd123.simpleorder.ui.intro

import androidx.compose.runtime.Composable

@Composable
fun IntroRoute(
    onNavigateToOrder: () -> Unit,
) {
    IntroScreen(
        onNext = onNavigateToOrder
    )
}