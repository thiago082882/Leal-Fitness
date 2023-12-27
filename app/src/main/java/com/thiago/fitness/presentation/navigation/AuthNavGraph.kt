package com.thiago.fitness.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.thiago.fitness.screens.login.LoginScreen
import com.thiago.fitness.screens.signup.SignUpScreen


fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Login.route
    ) {

        composable(route = AuthScreen.Login.route) {
            LoginScreen(navController)
        }

        composable(route = AuthScreen.Signup.route) {
            SignUpScreen(navController)
        }

    }
}

sealed class AuthScreen(val route: String) {

    data object Login : AuthScreen("login")
    data object Signup : AuthScreen("signup")

}