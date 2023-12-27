package com.thiago.fitness.screens.profile_update

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.thiago.fitness.presentation.components.DefaultTopBar
import com.thiago.fitness.screens.profile_update.component.ProfileUpdate
import com.thiago.fitness.screens.profile_update.component.ProfileUpdateContent
import com.thiago.fitness.screens.profile_update.component.SaveImage


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfileUpdateScreen(
    navController: NavHostController,
    user: String
) {
    Log.d("ProfileEditScreen", "User: $user")

    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Edit user",
                upAvailable = true,
                navController = navController
            )
        },
        content = {
            ProfileUpdateContent(navController = navController)
        },
        bottomBar = {}
    )
    SaveImage()
    ProfileUpdate()
}