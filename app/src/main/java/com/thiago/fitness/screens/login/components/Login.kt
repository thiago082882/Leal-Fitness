package com.thiago.fitness.screens.login.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.thiago.fitness.domain.model.Response
import com.thiago.fitness.presentation.components.ProgressBar
import com.thiago.fitness.presentation.navigation.Graph
import com.thiago.fitness.screens.login.LoginViewModel


@Composable
fun Login(navController: NavHostController, viewModel: LoginViewModel = hiltViewModel()) {

    when (val loginResponse = viewModel.loginResponse) {
        // MOSTRAR QUE SE ESTA REALIZANDO LA PETICION Y TODAVIA ESTA EN PROCESO
        Response.Loading -> {
            ProgressBar()
        }

        is Response.Success -> {
            LaunchedEffect(Unit) {
                navController.navigate(route = Graph.HOME) {
                    popUpTo(Graph.AUTHENTICATION) { inclusive = true }
                }
            }
        }

        is Response.Failure -> {
            Toast.makeText(
                LocalContext.current,
                loginResponse.exception?.message ?: "Error desconhecido",
                Toast.LENGTH_LONG
            ).show()
        }

        else -> {}
    }

}