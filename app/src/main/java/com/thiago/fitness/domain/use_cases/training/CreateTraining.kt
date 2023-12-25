package com.thiago.fitness.domain.use_cases.training

import com.thiago.fitness.domain.model.Training
import com.thiago.fitness.domain.repository.TrainingRepository
import java.io.File
import javax.inject.Inject

class CreateTraining @Inject constructor(private val repository: TrainingRepository) {

    suspend operator fun invoke(training: Training, file: File) = repository.create(training, file)


}