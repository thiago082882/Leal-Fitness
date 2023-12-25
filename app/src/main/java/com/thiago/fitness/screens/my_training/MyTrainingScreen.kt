package com.thiago.fitness.screens.my_training

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.thiago.fitness.presentation.navigation.DetailsScreen
import com.thiago.fitness.screens.my_training.components.GetTrainingsByIdUser

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MyTrainingScreen(navController: NavHostController) {

    Scaffold(
        content = {
            GetTrainingsByIdUser(navController = navController)
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(bottom = 50.dp),
                onClick = { navController.navigate(DetailsScreen.NewTraining.route) }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = ""
                )
            }
        }
    )

}