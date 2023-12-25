package com.thiago.fitness.presentation.navigation


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.thiago.fitness.screens.my_training.MyTrainingScreen
import com.thiago.fitness.screens.profile.ProfileScreen
import com.thiago.fitness.screens.training.TrainingScreen

@Composable
fun HomeBottomBarNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = HomeBottomBarScreen.Training.route
    ) {

        composable(route = HomeBottomBarScreen.Training.route) {
            TrainingScreen(navController)
        }

        composable(route = HomeBottomBarScreen.MyTraining.route) {
            MyTrainingScreen(navController)
        }

        composable(route = HomeBottomBarScreen.Profile.route) {
            ProfileScreen(navController)
        }

      detailsNavGraph(navController)

    }

}


sealed class HomeBottomBarScreen(
    val route: String,
    var title: String,
    val icon: ImageVector
) {

    data object Training : HomeBottomBarScreen(
        route = "training",
        title = "Training",
        icon = Icons.Default.FitnessCenter
    )

    data object MyTraining : HomeBottomBarScreen(
        route = "my_training",
        title = "My Training",
        icon = Icons.Outlined.List
    )

    data object Profile : HomeBottomBarScreen(
        route = "profile",
        title = "Profile",
        icon = Icons.Default.Person
    )

}