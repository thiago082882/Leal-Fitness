package com.thiago.fitness.screens.signup

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.thiago.fitness.presentation.components.DefaultTopBar
import com.thiago.fitness.presentation.ui.theme.AllFitnessTheme
import com.thiago.fitness.screens.signup.components.SignUp
import com.thiago.fitness.screens.signup.components.SignUpContent


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SignUpScreen(navController: NavHostController) {
    Scaffold(

        topBar = {

            DefaultTopBar(
                title = "Novo Usuario",
                upAvailable = true,
                navController = navController
            )
        },
        content = {
            SignUpContent(navController)
        },
        bottomBar = { }
    )
    SignUp(navController = navController)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSignUpScreen() {
    AllFitnessTheme(darkTheme = true) {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            SignUpScreen(rememberNavController())
        }
    }

}