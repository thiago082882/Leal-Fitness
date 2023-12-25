package com.thiago.fitness.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.thiago.fitness.presentation.navigation.HomeBottomBarNavGraph
import com.thiago.fitness.presentation.navigation.HomeBottomBarScreen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavHostController = rememberNavController()) {
    Scaffold(
        bottomBar = { BottomBar(navController =navController )}
    ) {
        HomeBottomBarNavGraph(navController = navController)
    }
}

@Composable

fun BottomBar (navController: NavHostController) {
    val screens = listOf(
        HomeBottomBarScreen.Training,
       HomeBottomBarScreen.MyTraining,
        HomeBottomBarScreen.Profile,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val bottomBarDestination = screens.any{it.route == currentDestination?.route}

    if (bottomBarDestination) {
        BottomNavigation {
            screens.forEach { screen ->
                AddItem(
                    screen = screen ,
                    currentDestination =currentDestination ,
                    navController = navController
                )

            }
        }
    }
}


@Composable
fun RowScope.AddItem(
    screen: HomeBottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController,
) {
    BottomNavigationItem(
        label = {
            Text(
                text = screen.title,
            )
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )


}