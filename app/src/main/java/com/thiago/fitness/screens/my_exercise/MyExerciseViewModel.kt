package com.thiago.fitness.screens.my_exercise

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thiago.fitness.domain.model.Exercise
import com.thiago.fitness.domain.model.Response
import com.thiago.fitness.domain.use_cases.auth.AuthUseCases
import com.thiago.fitness.domain.use_cases.exercise.ExerciseUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyExerciseViewModel @Inject constructor(
    private val exerciseUseCases: ExerciseUseCases,
    private val authUseCases: AuthUseCases
): ViewModel() {

    var exerciseResponse by mutableStateOf<Response<List<Exercise>>?>(null)
    var deleteResponse by mutableStateOf<Response<Boolean>?>(null)
    val currentUser = authUseCases.getCurrentUser()

    init {
        getExercise()
    }

    fun delete(idExercise: String) = viewModelScope.launch {
        deleteResponse = Response.Loading
        val result = exerciseUseCases.deleteExercise(idExercise)
        deleteResponse = result
    }

    fun getExercise() = viewModelScope.launch {
        exerciseResponse = Response.Loading
        exerciseUseCases.getExercisesByTrainingUseCases(currentUser?.uid ?: "").collect() { response ->
            exerciseResponse = response
        }
    }

}