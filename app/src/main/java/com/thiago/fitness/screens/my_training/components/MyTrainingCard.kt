package com.thiago.fitness.screens.my_training.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.thiago.fitness.domain.model.Training
import com.thiago.fitness.presentation.navigation.DetailsScreen
import com.thiago.fitness.screens.my_exercise.MyExerciseViewModel
import com.thiago.fitness.screens.my_training.MyTrainingViewModel


@Composable
fun MyTrainingCard(
    navController: NavHostController,
    training: Training,
    viewModel: MyTrainingViewModel = hiltViewModel()
) {
    Card(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .clickable {
                navController.navigate(route = DetailsScreen.ExerciseList.passExercise(training.toJson()))
            },
        elevation = 4.dp,
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp) // Altura aumentada para ajustar o tamanho da imagem
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(170.dp),
                    model = training.image,
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
                IconButton(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp),
                    onClick = {
                        navController.navigate(
                            route = DetailsScreen.UpdateTraining.passTraining(
                                training.toJson()
                            )
                        )
                    }
                ) {
                    Icon(
                        modifier = Modifier.size(25.dp),
                        imageVector = Icons.Default.Edit,
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            }
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Nome: ${training.name}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Descrição: ${training.description}",
                    fontSize = 14.sp,
                    maxLines = 3,
                    color = Color.Gray
                )
            }

        }
    }


}