package com.thiago.fitness.screens.training.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.thiago.fitness.domain.model.Response
import com.thiago.fitness.presentation.components.ProgressBar
import com.thiago.fitness.screens.training.TrainingViewModel


@Composable
fun GetTraining(navController: NavHostController, viewModel: TrainingViewModel = hiltViewModel()) {

    when (val response = viewModel.trainingResponse) {
        Response.Loading -> {
            ProgressBar()
        }

        is Response.Success -> {

            TrainingContent(
                navController = navController,
                trainings = response.data
            )
        }

        is Response.Failure -> {

            Toast.makeText(
                LocalContext.current,
                response.exception?.message ?: "Unknown error",
                Toast.LENGTH_LONG
            ).show()
        }

        else -> {}
    }
}