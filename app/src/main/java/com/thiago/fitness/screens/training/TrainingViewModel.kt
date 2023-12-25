package com.thiago.fitness.screens.training

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
class TrainingViewModel @Inject constructor(
    private val trainingUseCases: TrainingUseCases,
    private val authUseCases: AuthUseCases
): ViewModel() {
    var trainingResponse by mutableStateOf<Response<List<Training>>?>(null)
    init {
        getTraining()
    }


    fun getTraining() = viewModelScope.launch {
        trainingResponse = Response.Loading
        trainingUseCases.getTraining().collect() { response ->
           trainingResponse = response
        }
    }
}