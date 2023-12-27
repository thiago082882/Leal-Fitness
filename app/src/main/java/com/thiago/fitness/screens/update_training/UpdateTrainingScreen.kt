package com.thiago.fitness.screens.update_training

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.thiago.fitness.presentation.components.DefaultButton
import com.thiago.fitness.presentation.components.DefaultTopBar
import com.thiago.fitness.screens.update_training.components.UpdateTraining
import com.thiago.fitness.screens.update_training.components.UpdateTrainingContent

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UpdateTrainingScreen(navController: NavHostController, training: String, viewModel: UpdateTrainingViewModel = hiltViewModel()) {

    Scaffold(
        topBar = {
             DefaultTopBar(
                 title = "Edit Training",
                 upAvailable = true,
                 navController = navController
             )
        },
        content = {
            UpdateTrainingContent()
        },
        bottomBar = {
            DefaultButton(
                modifier = Modifier.fillMaxWidth(),
                text = "UPDATE",
                onClick = { viewModel.onUpdateTraining() }
            )
        }
    )
    UpdateTraining()

}