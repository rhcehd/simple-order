package dev.rhcehd123.simpleorder.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.rhcehd123.simpleorder.data.AppContainer
import dev.rhcehd123.simpleorder.ui.intro.IntroRoute
import dev.rhcehd123.simpleorder.ui.order.OrderRoute
import dev.rhcehd123.simpleorder.ui.order.OrderViewModel

@Composable
fun SimpleOrderNavGraph(
    appContainer: AppContainer,
    navController: NavHostController = rememberNavController(),
    startDestination: String = SimpleOrderDestinations.INTRO_ROUTE
) {
    val navigation = remember(navController) {
        SimpleOrderNavigationAction(navController)
    }
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(
            route = SimpleOrderDestinations.INTRO_ROUTE,
        ) {
            IntroRoute(
                onNavigateToOrder = navigation.navigateToOrder
            )
        }
        composable(
            route = SimpleOrderDestinations.ORDER_ROUTE,
        ) {
            val orderViewModel: OrderViewModel = viewModel(
                factory = OrderViewModel.provideFactory(
                    orderRepository = appContainer.orderRepository,
                )
            )
            OrderRoute(
                viewModel = orderViewModel,
                navigateToIntro = navigation.navigateToIntro
            )
        }
    }
}