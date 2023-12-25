package com.thiago.fitness.domain.use_cases.exercise


import com.thiago.fitness.domain.model.Exercise
import com.thiago.fitness.domain.model.Response
import com.thiago.fitness.domain.repository.ExerciseRepository
import com.thiago.fitness.domain.repository.TrainingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetExerciseByIdUser @Inject constructor(private val repository: ExerciseRepository) {
    operator fun invoke(idUser: String): Flow<Response<List<Exercise>>> {
        return repository.getExercisesByUserId(idUser)
    }
}
