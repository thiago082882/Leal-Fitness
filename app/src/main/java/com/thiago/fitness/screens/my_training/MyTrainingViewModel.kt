package com.thiago.fitness.screens.my_training

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thiago.fitness.domain.model.Response
import com.thiago.fitness.domain.model.Training
import com.thiago.fitness.domain.use_cases.auth.AuthUseCases
import com.thiago.fitness.domain.use_cases.training.TrainingUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyTrainingViewModel @Inject constructor(
    private val trainingUseCases: TrainingUseCases,
    private val authUseCases: AuthUseCases
): ViewModel() {

    var trainingResponse by mutableStateOf<Response<List<Training>>?>(null)
    var deleteResponse by mutableStateOf<Response<Boolean>?>(null)
    val currentUser = authUseCases.getCurrentUser()

    init {
      getPosts()
    }

    fun delete(idTraining: String) = viewModelScope.launch {
        deleteResponse = Response.Loading
        val result = trainingUseCases.deleteTraining(idTraining)
        deleteResponse = result
    }

    fun getPosts() = viewModelScope.launch {
        trainingResponse = Response.Loading
        trainingUseCases.getTrainingByIdUser(currentUser?.uid ?: "").collect() { response ->
            trainingResponse = response
        }
    }

}