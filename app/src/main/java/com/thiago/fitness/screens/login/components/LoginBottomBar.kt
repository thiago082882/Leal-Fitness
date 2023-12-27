package com.thiago.fitness.screens.login.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.thiago.fitness.presentation.navigation.AuthScreen
import com.thiago.fitness.presentation.ui.theme.Blue200

@Composable
fun LoginBottomBar(navController: NavHostController) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        horizontalArrangement = Arrangement.Center

    ) {
        Text(
            text = "Don't have an account?",
            fontSize = 14.sp,
        )
        Spacer(modifier = Modifier.width(7.dp))
        Text(
            modifier = Modifier.clickable {
                navController.navigate(route = AuthScreen.Signup.route)
            },
            text = "REGISTER HERE",
            color = Blue200,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold


        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginBottomBar() {

    LoginBottomBar(rememberNavController())

}
