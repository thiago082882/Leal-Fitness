package com.thiago.fitness.screens.profile_update.component

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.thiago.fitness.domain.model.Response
import com.thiago.fitness.presentation.components.ProgressBar
import com.thiago.fitness.screens.profile_update.ProfileUpdateViewModel

@Composable
fun SaveImage(viewModel: ProfileUpdateViewModel = hiltViewModel()) {

    when (val response = viewModel.saveImageResponse) {
        Response.Loading -> {
            ProgressBar()
        }

        is Response.Success -> {
            viewModel.onUpdate(response.data)
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