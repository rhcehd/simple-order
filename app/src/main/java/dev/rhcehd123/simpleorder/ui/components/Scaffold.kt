package dev.rhcehd123.simpleorder.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleOrderScaffold(
    onBack: () -> Unit,
    content: @Composable (Modifier) -> Unit,
) {
    Scaffold(
        topBar = {
            SimpleOrderTopAppBar(onBack)
        }
    ) { paddingValues ->
        content(Modifier.padding(paddingValues))
    }
}