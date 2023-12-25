package com.thiago.fitness.screens.training.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.thiago.fitness.domain.model.Training

@Composable
fun TrainingContent(
    navController: NavHostController,
    trainings: List<Training>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 55.dp)
    ) {
        items(
            items = trainings
        ) { training ->
            TrainingCard(navController = navController, training = training)
        }
    }
}