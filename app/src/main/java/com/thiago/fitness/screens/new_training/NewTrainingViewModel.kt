package com.thiago.fitness.screens.new_training

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thiago.fitness.domain.model.Response
import com.thiago.fitness.domain.model.Training
import com.thiago.fitness.domain.use_cases.auth.AuthUseCases
import com.thiago.fitness.domain.use_cases.training.TrainingUseCases
import com.thiago.fitness.presentation.utils.ComposeFileProvider
import com.thiago.fitness.presentation.utils.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class NewTrainingViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val trainingUseCase: TrainingUseCases,
    private val authUseCases: AuthUseCases,
) : ViewModel() {

    var state by mutableStateOf(NewTrainingState())

    // FILE
    var file: File? = null
    val resultingActivityHandler = ResultingActivityHandler()

    // RESPONSE
    var createPostResponse by mutableStateOf<Response<Boolean>?>(null)
        private set

    //USER SESSION
    val currentUser = authUseCases.getCurrentUser()


    fun createPost(training: Training) = viewModelScope.launch {
        createPostResponse = Response.Loading
        val result = trainingUseCase.createTraining(training, file!!)
        createPostResponse = result
    }

    fun onNewTraining() {
        val training = Training(
            name = state.name,
            description = state.description,
            category = state.category,
            data = state.data,
            idUser = currentUser?.uid ?: ""
        )
        createPost(training)
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
            category = "",
            description = "",
            image = ""
        )
        createPostResponse = null
    }

    fun onNameInput(name: String) {
        state = state.copy(name = name)
    }

    fun onCategoryInput(category: String) {
        state = state.copy(category = category)
    }

    fun onDescriptionInput(description: String) {
        state = state.copy(description = description)
    }

    fun onImageInput(image: String) {
        state = state.copy(image = image)
    }

}

data class CategoryRadioButton(
    var category: String,
    var image: Int
)