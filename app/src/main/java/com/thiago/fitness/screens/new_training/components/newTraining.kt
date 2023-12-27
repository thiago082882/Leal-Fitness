package com.thiago.fitness.screens.new_training.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.thiago.fitness.domain.model.Response
import com.thiago.fitness.presentation.components.ProgressBar
import com.thiago.fitness.screens.new_training.NewTrainingViewModel


@Composable
fun NewTraining(viewModel: NewTrainingViewModel = hiltViewModel()) {

    when (val response = viewModel.createPostResponse) {

        Response.Loading -> {
            ProgressBar()
        }

        is Response.Success -> {
            viewModel.clearForm()
            Toast.makeText(
                LocalContext.current,
                "The post was created correctly",
                Toast.LENGTH_LONG
            ).show()
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