package com.thiago.fitness.screens.new_exercise.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.thiago.fitness.R
import com.thiago.fitness.presentation.components.DefaultTextField
import com.thiago.fitness.presentation.components.DialogCapturePicture
import com.thiago.fitness.presentation.ui.theme.AllFitnessTheme
import com.thiago.fitness.presentation.ui.theme.Blue200
import com.thiago.fitness.screens.new_exercise.NewExerciseViewModel


@Composable
fun NewExerciseContent(viewModel: NewExerciseViewModel = hiltViewModel()) {

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
            .fillMaxWidth()


    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp)
                    .background(Blue200),
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (viewModel.state.image != "") {
                        AsyncImage(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(170.dp)
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
                            painter = painterResource(id = R.drawable.header),
                            contentDescription = "Imagem do usuario"
                        )
                        Text(
                            text = "SELECIONAR UMA IMAGEM",
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                }

            }
            DefaultTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp, start = 20.dp, end = 20.dp),
                value = state.name,
                onValueChange = { viewModel.onNameInput(it) },
                label = "Nome do Jogo",
                icon = Icons.Default.Face,
                errorMsg = "",
                validateField = {

                }
            )
            DefaultTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp, start = 20.dp, end = 20.dp),
                value = state.remarks,
                onValueChange = { viewModel.onDescriptionInput(it) },
                label = "Descrição",
                icon = Icons.Default.List,
                errorMsg = "",
                validateField = {

                }
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultNewPostContent() {
    AllFitnessTheme(darkTheme = true) {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            NewExerciseContent()
        }
    }
}