package dev.rhcehd123.simpleorder.ui

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

object SimpleOrderDestinations {
    const val INTRO_ROUTE = "intro"
    const val ORDER_ROUTE = "order"
}

class SimpleOrderNavigationAction(navController: NavHostController) {
    val navigateToIntro: () -> Unit = {
        navController.navigate(SimpleOrderDestinations.INTRO_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    val navigateToOrder: () -> Unit = {
        navController.navigate(SimpleOrderDestinations.ORDER_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}