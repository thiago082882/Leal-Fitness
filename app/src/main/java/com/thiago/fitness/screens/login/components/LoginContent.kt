package com.thiago.fitness.screens.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.thiago.fitness.R
import com.thiago.fitness.presentation.components.DefaultButton
import com.thiago.fitness.presentation.components.DefaultTextField
import com.thiago.fitness.presentation.ui.theme.Blue200
import com.thiago.fitness.presentation.ui.theme.DarkGray500
import com.thiago.fitness.screens.login.LoginViewModel


@Composable
fun LoginContent(navController: NavHostController, viewModel: LoginViewModel = hiltViewModel()) {

    val state = viewModel.state
    Box(
        modifier = Modifier
            .fillMaxWidth()

    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .background(Blue200)

        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Image(
                    modifier = Modifier.height(130.dp),
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "logo"
                )
                Text(
                    text = "LEAL FITNESS",

                    )
            }

        }

        Card(


            modifier = Modifier.padding(start = 40.dp, end = 40.dp, top = 200.dp),
            backgroundColor = DarkGray500,
        ) {

            Column(
                modifier = Modifier.padding(horizontal = 20.dp)

            ) {
                Text(
                    modifier = Modifier
                        .padding(
                            top = 40.dp,
                            bottom = 0.dp,
                            start = 0.dp,
                            end = 0.dp
                        ),

                    text = "LOGIN",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,

                    )
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Por favor inicie a sessão para continuar",
                    fontSize = 12.sp,

                    )
                DefaultTextField(
                    modifier = Modifier.padding(top = 25.dp),
                    value = state.email,
                    onValueChange = { viewModel.onEmailInput(it) },
                    label = "Email",
                    icon = Icons.Default.Email,
                    keyboardType = KeyboardType.Email,
                    errorMsg = viewModel.emailErrMsg,
                    validateField = {
                        viewModel.validateEmail()
                    },

                    )
                DefaultTextField(
                    modifier = Modifier.padding(top = 0.dp),
                    value = state.password,
                    onValueChange = { viewModel.onPasswordInput(it) },
                    label = "Senha",
                    icon = Icons.Default.Lock,
                    hideText = true,
                    errorMsg = viewModel.passwordErrMsg,
                    validateField = {
                        viewModel.validatePassword()
                    }
                )
                DefaultButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 40.dp),
                    text = "INICIAR SESSÃO",
                    onClick = {

                        viewModel.login()


                    },
                    enabled = viewModel.isEnabledLoginButton

                    )

            }
        }
    }

}


