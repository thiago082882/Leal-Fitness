package com.thiago.fitness.screens.profile_update.component


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.thiago.fitness.R
import com.thiago.fitness.presentation.components.DefaultButton
import com.thiago.fitness.presentation.components.DefaultTextField
import com.thiago.fitness.presentation.components.DialogCapturePicture
import com.thiago.fitness.presentation.ui.theme.Blue200
import com.thiago.fitness.presentation.ui.theme.DarkGray500
import com.thiago.fitness.screens.profile_update.ProfileUpdateViewModel


@Composable
fun ProfileUpdateContent(
    navController: NavHostController,
    viewModel: ProfileUpdateViewModel = hiltViewModel()
) {

    val state = viewModel.state
    viewModel.resultingActivityHandler.handle()
    var dialogState = remember { mutableStateOf(false) }

    DialogCapturePicture(
        status = dialogState,
        takePhoto = { viewModel.takePhoto() },
        pickImage = { viewModel.pickImage() }
    )
    Box(
        modifier = Modifier
            .fillMaxWidth(),


        ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
                .background(Blue200),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(30.dp))

                if (viewModel.state.image != "") {
                    Log.d("ProfileUpdateContent", "image: ${viewModel.state.image}")
                    AsyncImage(
                        modifier = Modifier
                            .height(100.dp)
                            .width(100.dp)
                            .clip(CircleShape)
                            .clickable {
                                dialogState.value = true
                            },
                        model = viewModel.state.image,
                        contentDescription = "Selected image",
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Image(
                        modifier = Modifier
                            .height(120.dp)
                            .clickable {
                                dialogState.value = true
                            },
                        painter = painterResource(id = R.drawable.user),
                        contentDescription = "Imagem de usuario"
                    )
                }

            }

        }
        Card(
            modifier = Modifier.padding(start = 40.dp, end = 40.dp, top = 170.dp),
            backgroundColor = DarkGray500
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
                    text = "ATUALIZAÇÃO",
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
                    modifier = Modifier.padding(top = 25.dp),
                    value = state.username,
                    onValueChange = { viewModel.onUsernameInput(it) },
                    label = "Nome de usuario",
                    icon = Icons.Default.Person,
                    errorMsg = viewModel.usernameErrMsg,
                    validateField = { viewModel.validateUsername() }
                )

                DefaultButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, bottom = 40.dp),
                    text = "EDITAR PERFIL",
                    onClick = { viewModel.saveImage() },
                )
            }
        }
    }

}
