package com.thiago.fitness.domain.use_cases.exercise

import com.thiago.fitness.domain.use_cases.training.TrainingUseCases

data class ExerciseUseCases(
    val createExercise: CreateExercise,
    val updateExercise: UpdateExercise,
    val getExercise: GetExercise,
    val deleteExercise : DeleteExercise,
    val getExerciseByIdUser: GetExerciseByIdUser,
    val getExercisesByTrainingUseCases: GetExercisesByUserIdUseCase
)