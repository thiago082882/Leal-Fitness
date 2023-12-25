package com.thiago.fitness.screens.signup.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.thiago.fitness.domain.model.Response
import com.thiago.fitness.presentation.components.ProgressBar
import com.thiago.fitness.presentation.navigation.Graph
import com.thiago.fitness.screens.signup.SignupViewModel

@Composable
fun SignUp(navController: NavHostController, viewModel: SignupViewModel = hiltViewModel()) {
    when(val signupResponse = viewModel.signupResponse) {
        Response.Loading -> {
            ProgressBar()
        }
        is Response.Success -> {
            LaunchedEffect(Unit) {
                viewModel.createUser()
                navController.popBackStack(Graph.AUTHENTICATION, true)
                navController.navigate(route = Graph.HOME)
            }
        }
        is Response.Failure -> {
            Toast.makeText(LocalContext.current, signupResponse.exception?.message ?: "Error desconhecido", Toast.LENGTH_LONG).show()
        }

        else -> {}
    }
}