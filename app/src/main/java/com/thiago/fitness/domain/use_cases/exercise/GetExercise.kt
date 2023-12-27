package com.thiago.fitness.domain.use_cases.exercise

import com.thiago.fitness.domain.repository.ExerciseRepository
import javax.inject.Inject

class GetExercise @Inject constructor(private val repository: ExerciseRepository) {

    operator fun invoke() = repository.getExercise()
}