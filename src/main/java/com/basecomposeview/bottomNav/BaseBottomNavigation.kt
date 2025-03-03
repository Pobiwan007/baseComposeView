package com.basecomposeview.bottomNav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.basecomposeview.row.SpaceAroundRow
import com.basecomposeview.row.SpaceBetweenRow

@Composable
fun BaseBottomNavigation(
    modifier: Modifier = Modifier,
    navController: NavController,
    routes: List<BaseRoute>,
    newBottomItem: @Composable (route: BaseRoute, onClick: () -> Unit, isSelected: Boolean) -> Unit
) {
    val navStackBackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navStackBackEntry?.destination

    SpaceAroundRow(modifier = modifier) {
        routes.forEach { route ->
            val isSelected = currentDestination?.hierarchy?.any {
                it.hasRoute(route::class)
            } == true
            newBottomItem(
                route,
                {
                    if (isSelected) {
                        navController.navigate(route) {
                            popUpTo(route) { inclusive = false }
                            launchSingleTop = true
                            restoreState = false
                        }
                    } else {
                        navController.navigate(route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                isSelected
            )
        }
    }
}