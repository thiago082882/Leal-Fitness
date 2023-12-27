package com.thiago.fitness.domain.use_cases.training

data class TrainingUseCases(
    val createTraining: CreateTraining,
    val getTraining: GetTraining,
    val getTrainingByIdUser: GetTrainingByIdUser,
    val deleteTraining: DeleteTraining,
    val updateTraining: UpdateTraining
)