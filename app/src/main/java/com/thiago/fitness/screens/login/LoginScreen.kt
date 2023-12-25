package com.thiago.fitness.screens.login

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.thiago.fitness.presentation.ui.theme.AllFitnessTheme
import com.thiago.fitness.screens.login.components.Login
import com.thiago.fitness.screens.login.components.LoginBottomBar
import com.thiago.fitness.screens.login.components.LoginContent


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreen(navController: NavHostController) {


    Scaffold(
        topBar = {},
        content = {
            LoginContent(navController=navController)
        },
        bottomBar = {

            LoginBottomBar(navController=navController)
        }

    )

    Login(navController = navController)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    AllFitnessTheme(darkTheme = true) {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            LoginScreen(rememberNavController())
        }
    }
}