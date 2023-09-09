package dev.rhcehd123.simpleorder.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dev.rhcehd123.simpleorder.SimpleOrderApplication
import dev.rhcehd123.simpleorder.ui.SimpleOrderApp
import dev.rhcehd123.simpleorder.ui.theme.SimpleOrderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appContainer = (application as SimpleOrderApplication).container
        setContent {
            SimpleOrderTheme {
                SimpleOrderApp(appContainer)
            }
        }
    }
}