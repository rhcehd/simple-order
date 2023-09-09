package dev.rhcehd123.simpleorder.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import dev.rhcehd123.simpleorder.data.AppContainer

@Composable
fun SimpleOrderApp(
    appContainer: AppContainer
) {

    SimpleOrderNavGraph(appContainer = appContainer)
}