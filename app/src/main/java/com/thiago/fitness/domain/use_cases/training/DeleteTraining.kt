package com.thiago.fitness.domain.use_cases.training

import com.thiago.fitness.domain.repository.TrainingRepository
import javax.inject.Inject

class DeleteTraining  @Inject constructor(private val repository: TrainingRepository){
    suspend operator fun invoke(idTraining:String) = repository.delete(idTraining)
}