package com.thiago.fitness.screens.training.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.google.firebase.Timestamp
import com.thiago.fitness.domain.model.Training
import com.thiago.fitness.presentation.navigation.DetailsScreen
import com.thiago.fitness.presentation.utils.formatTimestamp
import com.thiago.fitness.screens.training.TrainingViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun TrainingCard(navController: NavHostController, training: Training, viewModel: TrainingViewModel = hiltViewModel()) {



    Card(
        modifier = Modifier
            .padding(top = 0.dp, bottom = 15.dp)
            .clickable {
              navController.navigate(route = DetailsScreen.DetailTraining.passTraining(training.toJson()))
            },
        elevation = 4.dp,
        shape = RoundedCornerShape(20.dp),
        contentColor = Color.White,

    ) {
        Column() {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp),
                model = training.image,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp),
                text = training.name,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 3.dp),
                text = training.user?.username ?: "",
                fontSize = 12.sp
            )
            Text(
                modifier = Modifier.padding(start = 15.dp, end = 15.dp, top = 3.dp, bottom = 10.dp),
                text = training.description,
                fontSize = 13.sp,
                maxLines = 2,
                color = Color.Gray
            )

            Text(
                modifier = Modifier.padding(start = 15.dp, end = 15.dp, top = 3.dp, bottom = 10.dp),
                text = formatTimestamp(training.data),
                fontSize = 13.sp,
                maxLines = 2,
                color = Color.Gray
            )

        }
        }

}