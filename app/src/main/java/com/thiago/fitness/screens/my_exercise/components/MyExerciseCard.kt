
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.thiago.fitness.domain.model.Exercise
import com.thiago.fitness.presentation.navigation.DetailsScreen
import com.thiago.fitness.screens.my_exercise.MyExerciseViewModel

@Composable
fun MyExerciseCard(
    navController: NavHostController,
    exercise: Exercise,
    viewModel: MyExerciseViewModel = hiltViewModel()
) {


    Card(
        modifier = Modifier
            .padding(top = 0.dp, bottom = 15.dp)
            .clickable {

               // navController.navigate(route = DetailsScreen.ExerciseList.passExercise(trainingId = exercise.toJson()))
            },
        elevation = 4.dp,
        shape = RoundedCornerShape(20.dp),
        contentColor = Color.White,

        ) {
        Column() {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp),
                model = exercise.image,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp),
                text = exercise.name,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.padding(start = 15.dp, end = 15.dp, top = 3.dp),
                text = exercise.remarks,
                fontSize = 13.sp,
                maxLines = 2,
                color = Color.Gray
            )
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                IconButton(
//                    onClick = {
//                        navController.navigate(
//                            route = DetailsScreen.UpdateTraining.passTraining(
//                                exercise.trainingId
//                            )
//                        )
//                    }
//                ) {
//                    Icon(
//                        modifier = Modifier.size(25.dp),
//                        imageVector = Icons.Default.Edit,
//                        contentDescription = "",
//                        tint = Color.White
//                    )
//                }
//            }
        }
    }

}