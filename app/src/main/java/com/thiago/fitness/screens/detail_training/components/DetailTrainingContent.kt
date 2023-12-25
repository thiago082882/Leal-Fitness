package com.thiago.fitness.screens.detail_training.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.thiago.fitness.presentation.ui.theme.AllFitnessTheme
import com.thiago.fitness.presentation.ui.theme.Blue200
import com.thiago.fitness.screens.detail_training.DetailTrainingViewModel


@Composable
fun DetailTrainingContent(
    navController: NavHostController,
    viewModel: DetailTrainingViewModel = hiltViewModel(),
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {

        Box() {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                model = viewModel.training.image,
                contentDescription = "",
                contentScale = ContentScale.Crop

            )
            IconButton(onClick = { navController?.popBackStack() }) {
                Icon(
                    modifier = Modifier.size(35.dp),
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "",
                    tint = Color.White
                )
            }
        }
        if (!viewModel.training.user?.username.isNullOrBlank()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp, horizontal = 15.dp),
                elevation = 4.dp,
                shape = RoundedCornerShape(12.dp)
            ) {

                Row(
                    modifier = Modifier.padding(vertical = 18.dp, horizontal = 15.dp)

                ) {
                    AsyncImage(
                        modifier = Modifier
                            .size(55.dp)
                            .clip(CircleShape),
                        model = viewModel.training.user?.image,
                        contentDescription = "",
                        contentScale = ContentScale.Crop

                    )
                    Column(
                        modifier = Modifier.padding(top = 7.dp, start = 20.dp)
                    ) {
                        Text(
                            text = viewModel.training.user?.username!!,
                            fontSize = 14.sp
                        )
                        Text(
                            text = viewModel.training.user?.email!!,
                            fontSize = 12.sp
                        )
                    }

                }

            }
        } else {
            Spacer(modifier = Modifier.height(15.dp))
        }

        Text(
            modifier = Modifier.padding(start = 20.dp, bottom = 15.dp),
            text = viewModel.training.name,
            fontSize = 20.sp,
            color = Blue200,
            fontWeight = FontWeight.Bold
        )
        Card(
            modifier = Modifier.padding(start = 13.dp, bottom = 15.dp),
            elevation = 4.dp,
            shape = RoundedCornerShape(20.dp)
        ) {
            Row(
                modifier = Modifier.padding(vertical = 7.dp, horizontal = 20.dp),
                horizontalArrangement = Arrangement.Center
            ) {

                Spacer(
                    modifier = Modifier.width(7.dp)
                )
                Text(
                    text = viewModel.training.category,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
            }

        }
        Divider(
            modifier = Modifier.padding(end = 20.dp, top = 10.dp, bottom = 10.dp),
            startIndent = 20.dp,
            thickness = 1.dp,
            color = Color.DarkGray
        )

        Text(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
            text = "DESCRIÇÃO",
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp
        )
        Text(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 5.dp),
            text = viewModel.training.description,
            fontSize = 14.sp
        )
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewDetailPostContent() {
    AllFitnessTheme(darkTheme = true) {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            DetailTrainingContent(rememberNavController())
        }
    }
}
