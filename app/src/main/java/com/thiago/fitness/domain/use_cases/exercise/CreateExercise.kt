package com.thiago.fitness.domain.use_cases.exercise

import com.thiago.fitness.domain.model.Exercise
import com.thiago.fitness.domain.model.Training
import com.thiago.fitness.domain.repository.ExerciseRepository
import com.thiago.fitness.domain.repository.TrainingRepository
import java.io.File
import javax.inject.Inject

class CreateExercise @Inject constructor(private val repository: ExerciseRepository) {

    suspend operator fun invoke(exercise: Exercise, file: File) = repository.create(exercise, file)


}