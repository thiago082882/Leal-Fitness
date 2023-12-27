package com.thiago.fitness.screens.update_training.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.thiago.fitness.domain.model.Response
import com.thiago.fitness.presentation.components.ProgressBar
import com.thiago.fitness.screens.update_training.UpdateTrainingViewModel

@Composable
fun UpdateTraining(viewModel: UpdateTrainingViewModel = hiltViewModel()) {

    when (val response = viewModel.updatePostResponse) {

        Response.Loading -> {
            ProgressBar()
        }

        is Response.Success -> {
            viewModel.clearForm()
            Toast.makeText(
                LocalContext.current,
                "The post has been updated correctly",
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