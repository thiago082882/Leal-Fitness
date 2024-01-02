package com.thiago.fitness.screens.new_exercise

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.thiago.fitness.core.Constants
import com.thiago.fitness.domain.model.Exercise
import com.thiago.fitness.domain.model.Response
import com.thiago.fitness.domain.model.Training
import com.thiago.fitness.domain.use_cases.auth.AuthUseCases
import com.thiago.fitness.domain.use_cases.exercise.ExerciseUseCases
import com.thiago.fitness.presentation.utils.ComposeFileProvider
import com.thiago.fitness.presentation.utils.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class NewExerciseViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val exerciseUseCase: ExerciseUseCases,
    private val authUseCases: AuthUseCases,
) : ViewModel() {

    var state by mutableStateOf(NewExerciseState())

    var file: File? = null
    val resultingActivityHandler = ResultingActivityHandler()


    var createPostResponse by mutableStateOf<Response<Boolean>?>(null)
        private set


    val currentUser = authUseCases.getCurrentUser()

    val training = Training()
    val docRef = FirebaseFirestore.getInstance().collection(Constants.TRAINING).document()


    fun createExercise(exercise: Exercise, file: File, trainingId: String) = viewModelScope.launch {
        createPostResponse = Response.Loading
        val result = exerciseUseCase.createExercise(exercise, file, trainingId)
        createPostResponse = result
    }

    fun onNewExercise() {
        val exercise = Exercise(
            name = state.name,
            remarks = state.remarks,
            trainingId =  docRef.id
        )
        createExercise(exercise, file!!, docRef.id)
    }


    fun pickImage() = viewModelScope.launch {
        val result = resultingActivityHandler.getContent("image/*")
        if (result != null) {
            file = ComposeFileProvider.createFileFromUri(context, result)
            state = state.copy(image = result.toString())
        }
    }

    fun takePhoto() = viewModelScope.launch {
        val result = resultingActivityHandler.takePicturePreview()
        if (result != null) {
            state = state.copy(image = ComposeFileProvider.getPathFromBitmap(context, result))
            file = File(state.image)
        }
    }

    fun clearForm() {
        state = state.copy(
            name = "",
            remarks = "",
            image = ""
        )
        createPostResponse = null
    }

    fun onNameInput(name: String) {
        state = state.copy(name = name)
    }


    fun onDescriptionInput(description: String) {
        state = state.copy(remarks = description)
    }

    fun onImageInput(image: String) {
        state = state.copy(image = image)
    }

}

data class CategoryRadioButton(
    var category: String,
    var image: Int
)