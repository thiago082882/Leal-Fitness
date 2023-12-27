package com.thiago.fitness.screens.new_exercise

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


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NewExerciseScreen(navController: NavHostController, viewModel: NewExerciseViewModel = hiltViewModel()) {

    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "New Exercise",
                upAvailable = true,
                navController = navController
            )
        },
        content = {
            NewExerciseContent()
        },
        bottomBar = {
            DefaultButton(
                modifier = Modifier.fillMaxWidth(),
                text = "PUBLISH",
                onClick = { viewModel.onNewExercise() }
            )
        }
    )
    NewExercise()

}