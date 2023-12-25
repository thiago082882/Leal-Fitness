package com.thiago.fitness.screens.signup.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.Card

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.thiago.fitness.R
import com.thiago.fitness.domain.model.Response
import com.thiago.fitness.presentation.components.DefaultButton
import com.thiago.fitness.presentation.components.DefaultTextField
import com.thiago.fitness.presentation.ui.theme.DarkGray500
import com.thiago.fitness.presentation.ui.theme.Blue200
import com.thiago.fitness.screens.signup.SignupViewModel



@Composable
fun SignUpContent(navController : NavHostController, viewModel: SignupViewModel = hiltViewModel()) {

    val state = viewModel.state
    Box(
        modifier = Modifier
            .fillMaxWidth()

    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .background(Blue200) //trocar

        ) {

            Column(
                modifier = Modifier.fillMaxWidth().padding(top = 45.dp),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Image(
                    modifier = Modifier.height(120.dp),
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = "Image in user"
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
                            top = 20.dp,
                            bottom = 0.dp,
                            start = 0.dp,
                            end = 0.dp
                        ),

                    text = "REGISTRAR",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Por favor inserir seus dados para continuar",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                DefaultTextField(
                    modifier = Modifier.padding(top = 10.dp),
                    value = state.username,
                    onValueChange = { viewModel.onUsernameInput(it) },
                    label = "Nome de usuario",
                    icon = Icons.Default.Person,
                    errorMsg = viewModel.usernameErrMsg,
                    validateField = { viewModel.validateUsername() }
                )

                DefaultTextField(
                    modifier = Modifier.padding(top = 0.dp),
                    value = state.email,
                    onValueChange = { viewModel.onEmailInput(it) },
                    label = "email",
                    icon = Icons.Default.Email,
                    keyboardType = KeyboardType.Email,
                    errorMsg = viewModel.emailErrMsg,
                    validateField = { viewModel.validateEmail() }
                )
                DefaultTextField(
                    modifier = Modifier.padding(top = 0.dp),
                    value = state.password,
                    onValueChange = { viewModel.onPasswordInput(it) },
                    label = "senha",
                    icon = Icons.Default.Lock,
                    hideText = true,
                    errorMsg = viewModel.passwordErrMsg,
                    validateField = { viewModel.validatePassword() }
                )
                DefaultTextField(
                    modifier = Modifier.padding(top = 0.dp),
                    value = state.confirmPassword,
                    onValueChange = { viewModel.onConfirmPasswordInput(it) },
                    label = "Confirmar senha",
                    icon = Icons.Outlined.Lock,
                    hideText = true,
                    errorMsg = viewModel.confirmPasswordErrMsg,
                    validateField = { viewModel.validateConfirmPassword() }
                )
                DefaultButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp),
                    text = "REGISTRAR",
                    onClick = { viewModel.onSignup() },
                    enabled = viewModel.isEnabledLoginButton
                )

            }

        }
    }
}

