package com.thiago.fitness.domain.repository

import com.thiago.fitness.domain.model.Exercise
import com.thiago.fitness.domain.model.Response
import kotlinx.coroutines.flow.Flow
import java.io.File

interface ExerciseRepository {


    fun getExercise(): Flow<Response<List<Exercise>>>
    fun getExercisesByUserId(idUser: String): Flow<Response<List<Exercise>>>
    fun getExercisesByTrainingId(trainingId: String): Flow<Response<List<Exercise>>>
    suspend fun create(exercise: Exercise, file: File,trainingId: String): Response<Boolean>
    suspend fun update(exercise: Exercise, file: File?): Response<Boolean>
    suspend fun delete(idExercise: String): Response<Boolean>


}