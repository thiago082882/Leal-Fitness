package com.thiago.fitness.domain.use_cases.exercise

import com.thiago.fitness.domain.model.Exercise
import com.thiago.fitness.domain.repository.ExerciseRepository
import java.io.File
import javax.inject.Inject

class CreateExercise @Inject constructor(private val repository: ExerciseRepository) {

    suspend operator fun invoke(exercise: Exercise, file: File,trainingId: String) = repository.create(exercise, file,trainingId)


}