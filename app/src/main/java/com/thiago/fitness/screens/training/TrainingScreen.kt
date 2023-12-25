package com.thiago.fitness.screens.training

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.thiago.fitness.screens.training.components.GetTraining

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TrainingScreen(navController: NavHostController, viewModel: TrainingViewModel = hiltViewModel()) {

    Scaffold(
        content = {
            GetTraining(navController)
        }
    )

}