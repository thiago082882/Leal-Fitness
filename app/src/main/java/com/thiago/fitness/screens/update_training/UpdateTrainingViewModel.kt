package com.thiago.fitness.screens.update_training

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
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
class UpdateTrainingViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val savedStateHandle: SavedStateHandle,
    private val trainingUseCases: TrainingUseCases,
    private val authUseCases: AuthUseCases,
) : ViewModel() {

    var state by mutableStateOf(UpdatePostState())


    var file: File? = null
    val resultingActivityHandler = ResultingActivityHandler()


    val data = savedStateHandle.get<String>("training")
    val training = Training.fromJson(data!!)


    var updatePostResponse by mutableStateOf<Response<Boolean>?>(null)
        private set

    val currentUser = authUseCases.getCurrentUser()

    init {
        state = state.copy(
            name = training.name,
            description = training.description,
            image = training.image,
            category = training.category,
        )
    }

    fun updateTraining(training: Training) = viewModelScope.launch {
        updatePostResponse = Response.Loading
        val result = trainingUseCases.updateTraining(training, file)
        updatePostResponse = result
    }

    fun onUpdateTraining() {
        val training = Training(
            id = training.id,
            name = state.name,
            description = state.description,
            category = state.category,
            image = training.image,
            idUser = currentUser?.uid ?: ""
        )
        updateTraining(training)
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
            name ="",
            category = "",
            description = "",
            image = ""
        )
        updatePostResponse = null
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