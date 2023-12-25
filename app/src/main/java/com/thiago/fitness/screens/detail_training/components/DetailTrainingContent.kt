package com.thiago.fitness.screens.detail_training.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import com.thiago.fitness.R
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
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = viewModel.training.image,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            IconButton(
                onClick = { navController?.popBackStack() },
                modifier = Modifier
                    .padding(16.dp)
                    .size(35.dp)
                    .align(Alignment.TopStart)
            ) {
                Icon(
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
                    .padding(16.dp),
                elevation = 8.dp,
                shape = RoundedCornerShape(16.dp)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                            .border(2.dp, MaterialTheme.colors.secondary, CircleShape),
                        model = viewModel.training.user?.image ?: R.drawable.user,
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Column {
                        Text(
                            text = viewModel.training.user?.username!!,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = viewModel.training.user?.email!!,
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                }
            }

        } else {
            Spacer(modifier = Modifier.height(16.dp))
        }

        Text(
            modifier = Modifier.padding(start = 20.dp, bottom = 15.dp),
            text = viewModel.training.name,
            fontSize = 22.sp,
            color = Blue200,
            fontWeight = FontWeight.Bold
        )
        Card(
            modifier = Modifier.padding(start = 13.dp, bottom = 15.dp),
            elevation = 8.dp,
            shape = RoundedCornerShape(24.dp)
        ) {
            Row(
                modifier = Modifier.padding(vertical = 10.dp, horizontal = 24.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Spacer(
                    modifier = Modifier.width(10.dp)
                )
                Text(
                    text = viewModel.training.category,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        }
        Divider(
            modifier = Modifier.padding(end = 20.dp, top = 12.dp, bottom = 12.dp),
            startIndent = 20.dp,
            thickness = 2.dp,
            color = Color.DarkGray
        )
        Text(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp),
            text = "DESCRIÇÃO",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Text(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 7.dp),
            text = viewModel.training.description,
            fontSize = 16.sp
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
