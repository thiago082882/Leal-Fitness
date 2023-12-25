package com.thiago.fitness.screens.profile_update.component

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.thiago.fitness.domain.model.Response
import com.thiago.fitness.presentation.components.ProgressBar
import com.thiago.fitness.screens.profile_update.ProfileUpdateViewModel

@Composable
fun ProfileUpdate(viewModel: ProfileUpdateViewModel = hiltViewModel()) {

    when(val updateResponse = viewModel.updateResponse) {
        Response.Loading -> {
            ProgressBar()
        }
        is Response.Success -> {
            Toast.makeText(LocalContext.current, "Dados atualizados corretamente", Toast.LENGTH_LONG).show()
        }
        is Response.Failure -> {
            Toast.makeText(LocalContext.current, updateResponse.exception?.message ?: "Error desconhecido" , Toast.LENGTH_LONG).show()
        }

        else -> {}
    }

}