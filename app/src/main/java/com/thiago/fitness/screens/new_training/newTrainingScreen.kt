package com.thiago.fitness.screens.new_training

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.thiago.fitness.presentation.components.DefaultButton
import com.thiago.fitness.presentation.components.DefaultTopBar
import com.thiago.fitness.screens.new_exercise.components.NewExercise
import com.thiago.fitness.screens.new_exercise.components.NewExerciseContent
import com.thiago.fitness.screens.new_training.components.NewTraining
import com.thiago.fitness.screens.new_training.components.NewTrainingContent


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NewTrainingScreen(navController: NavHostController, viewModel: NewTrainingViewModel = hiltViewModel()) {

    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "New Training",
                upAvailable = true,
                navController = navController
            )
        },
        content = {
            NewTrainingContent()
        },
        bottomBar = {
            DefaultButton(
                modifier = Modifier.fillMaxWidth(),
                text = "PUBLISH",
                onClick = { viewModel.onNewTraining() }
            )
        }
    )
    NewTraining()

}