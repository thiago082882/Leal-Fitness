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

    when(val response = viewModel.createPostResponse) {
        // MOSTRAR QUE SE ESTA REALIZANDO LA PETICION Y TODAVIA ESTA EN PROCESO
        Response.Loading -> {
            ProgressBar()
        }
        is Response.Success -> {
            viewModel.clearForm()
            Toast.makeText(LocalContext.current, "A publicaão foi criada corretamente", Toast.LENGTH_LONG).show()
        }
        is Response.Failure -> {
            Toast.makeText(LocalContext.current, response.exception?.message ?: "Error desconhecido", Toast.LENGTH_LONG).show()
        }

        else -> {}
    }

}