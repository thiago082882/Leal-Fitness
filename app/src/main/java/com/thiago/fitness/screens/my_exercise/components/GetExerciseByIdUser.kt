package com.thiago.fitness.screens.my_exercise.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.thiago.fitness.domain.model.Response
import com.thiago.fitness.presentation.components.ProgressBar
import com.thiago.fitness.screens.my_exercise.MyExerciseViewModel


@Composable
fun GetExerciseByIdUser(
    navController: NavHostController,
    viewModel: MyExerciseViewModel = hiltViewModel()
) {

    when (val response = viewModel.exerciseResponse) {
        Response.Loading -> {
            ProgressBar()
        }

        is Response.Success -> {

            MyExerciseContent(
                navController = navController,
                exercises = response.data

            )
        }

        is Response.Failure -> {

            Toast.makeText(
                LocalContext.current,
                response.exception?.message ?: "Error desconhecido",
                Toast.LENGTH_LONG
            ).show()
        }

        else -> {}
    }
}