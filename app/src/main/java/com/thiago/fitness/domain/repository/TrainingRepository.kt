package com.thiago.fitness.domain.repository

import com.thiago.fitness.domain.model.Response
import com.thiago.fitness.domain.model.Training
import kotlinx.coroutines.flow.Flow
import java.io.File

interface TrainingRepository {


    fun getTraining(): Flow<Response<List<Training>>>
    fun getTrainingByUserId(idUser: String): Flow<Response<List<Training>>>
    suspend fun create(training: Training, file: File): Response<Boolean>
    suspend fun update(training: Training,file: File?): Response<Boolean>
    suspend fun delete(idTraining: String): Response<Boolean>


}