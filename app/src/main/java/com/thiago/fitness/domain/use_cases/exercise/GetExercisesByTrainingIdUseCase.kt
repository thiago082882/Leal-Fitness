package com.thiago.fitness.domain.use_cases.exercise


import com.thiago.fitness.domain.model.Exercise
import com.thiago.fitness.domain.model.Response
import com.thiago.fitness.domain.repository.ExerciseRepository
import kotlinx.coroutines.flow.Flow

class GetExercisesByUserIdUseCase(private val exerciseRepository: ExerciseRepository) {
    operator fun invoke(idUser: String): Flow<Response<List<Exercise>>> {
        return exerciseRepository.getExercisesByTrainingId(idUser)
    }
}
