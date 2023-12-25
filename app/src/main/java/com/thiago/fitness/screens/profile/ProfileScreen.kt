package com.thiago.fitness.screens.profile

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.thiago.fitness.screens.profile.components.ProfileContent


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfileScreen(navController: NavHostController,viewModel: ProfileViewModel= hiltViewModel()) {

    Scaffold (
        topBar = {},
        content = {
           ProfileContent(navController)
        },
        bottomBar = {}
            )

    }
