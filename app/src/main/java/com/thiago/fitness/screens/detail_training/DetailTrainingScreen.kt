package com.thiago.fitness.screens.detail_training
import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.thiago.fitness.screens.detail_training.components.DetailTrainingContent


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
 fun DetailTrainingScreen (navController: NavHostController,training : String){
     Scaffold(
        content = {
            DetailTrainingContent(navController)
        }
     )

 }


