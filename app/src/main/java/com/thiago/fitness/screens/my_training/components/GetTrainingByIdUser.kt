package com.thiago.fitness.screens.my_training.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.thiago.fitness.domain.model.Exercise
import com.thiago.fitness.domain.model.Response
import com.thiago.fitness.presentation.components.ProgressBar
import com.thiago.fitness.screens.my_training.MyTrainingViewModel


@Composable
fun GetTrainingsByIdUser(
    navController: NavHostController,
    viewModel: MyTrainingViewModel = hiltViewModel()
) {

    when (val response = viewModel.trainingResponse) {
        Response.Loading -> {
            ProgressBar()
        }

        is Response.Success -> {

           MyTrainingContent(
                navController = navController,
                trainings = response.data

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