package com.thiago.fitness.domain.use_cases.training

import com.thiago.fitness.domain.repository.TrainingRepository
import javax.inject.Inject

class GetTrainingByIdUser @Inject constructor(private val repository: TrainingRepository) {
    operator fun invoke(idUser: String) = repository.getTrainingByUserId(idUser)
}