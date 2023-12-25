package com.thiago.fitness.domain.use_cases.training

import com.thiago.fitness.domain.repository.TrainingRepository
import javax.inject.Inject

class GetTraining @Inject constructor(private val repository: TrainingRepository) {

operator fun invoke() = repository.getTraining()
}