package com.thiago.fitness.domain.use_cases.exercise



import com.thiago.fitness.domain.model.Exercise
import com.thiago.fitness.domain.model.Response
import com.thiago.fitness.domain.repository.ExerciseRepository
import kotlinx.coroutines.flow.Flow

class GetExercisesByUserIdUseCase(private val exerciseRepository: ExerciseRepository) {
    suspend fun invoke(idUser: String): Flow<Response<List<Exercise>>> {
        return exerciseRepository.getExercisesByUserId(idUser)
    }
}
