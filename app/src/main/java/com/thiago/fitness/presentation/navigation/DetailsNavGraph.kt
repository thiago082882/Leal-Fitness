package com.thiago.fitness.presentation.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.thiago.fitness.screens.new_training.NewTrainingScreen
import com.thiago.fitness.screens.detail_training.DetailTrainingScreen
import com.thiago.fitness.screens.profile_update.ProfileUpdateScreen
import com.thiago.fitness.screens.update_training.UpdateTrainingScreen

fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {

    navigation(
        route = Graph.DETAILS,
        startDestination = DetailsScreen.ProfileUpdate.route
    ) {

        composable(route = DetailsScreen.NewTraining.route) {
            NewTrainingScreen(navController = navController)
        }

        composable(
            route = DetailsScreen.ProfileUpdate.route,
            arguments = listOf(navArgument("user"){
                type = NavType.StringType
            })
        ) {
            it.arguments?.getString("user")?.let {
               ProfileUpdateScreen(navController, user = it)
            }
        }

        composable(
            route = DetailsScreen.DetailTraining.route,
            arguments = listOf(navArgument("training"){
                type = NavType.StringType
            })
        ) {
            it.arguments?.getString("training")?.let {
                DetailTrainingScreen(navController, training = it)
            }
        }

        composable(
            route = DetailsScreen.UpdateTraining.route,
            arguments = listOf(navArgument("training"){
                type = NavType.StringType
            })
        ) {
            it.arguments?.getString("training")?.let {
                UpdateTrainingScreen(navController, training = it)
            }
        }
    }

}

sealed class DetailsScreen(val route: String) {

    data object NewTraining: DetailsScreen("training/new")
    data object ProfileUpdate: DetailsScreen("profile/update/{user}") {
        fun passUser(user: String) = "profile/update/$user"
    }
    data object DetailTraining: DetailsScreen("training/detail/{training}") {
        fun passTraining(training: String) = "training/detail/$training"
    }
    data object UpdateTraining: DetailsScreen("training/update/{training}") {
        fun passTraining(training: String) = "training/update/$training"
    }

}