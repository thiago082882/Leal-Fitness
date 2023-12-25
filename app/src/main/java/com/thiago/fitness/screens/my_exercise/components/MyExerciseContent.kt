package com.thiago.fitness.screens.my_exercise.components

import MyExerciseCard
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.thiago.fitness.domain.model.Exercise

@Composable
fun MyExerciseContent(
    navController: NavHostController,
    exercises: List<Exercise>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 55.dp)
    ) {
        items(
            items = exercises
        ) { exercise ->
            MyExerciseCard(navController = navController, exercise = exercise)
        }
    }
}
