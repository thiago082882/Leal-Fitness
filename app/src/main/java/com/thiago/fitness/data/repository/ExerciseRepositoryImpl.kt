package com.thiago.fitness.data.repository

import android.net.Uri
import android.util.Log
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.storage.StorageReference
import com.thiago.fitness.core.Constants.EXERCISE
import com.thiago.fitness.core.Constants.TRAINING
import com.thiago.fitness.domain.model.Exercise
import com.thiago.fitness.domain.model.Response
import com.thiago.fitness.domain.model.Training
import com.thiago.fitness.domain.repository.ExerciseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject
import javax.inject.Named

class ExerciseRepositoryImpl @Inject constructor(
    @Named(EXERCISE) private val exerciseRef: CollectionReference,
    @Named(TRAINING) private val trainingRef: CollectionReference,
    @Named(EXERCISE) private val storageExerciseRef: StorageReference,
):
    ExerciseRepository {
    override fun getExercise(): Flow<Response<List<Exercise>>> = callbackFlow {
        val snapshotListener = exerciseRef.addSnapshotListener { snapshot, e ->
            GlobalScope.launch(Dispatchers.IO) {
                val exerciseResponse = if (snapshot != null) {
                    val exercises = snapshot.toObjects(Exercise::class.java)
                    snapshot.documents.forEachIndexed { index, document ->
                        exercises[index].id = document.id
                    }
                    Response.Success(exercises)
                } else {
                    Response.Failure(e)
                }
                trySend(exerciseResponse)
            }
        }
        awaitClose {
            snapshotListener.remove()
        }
    }


    override fun getExercisesByUserId(idUser: String): Flow<Response<List<Exercise>>> = flow {
        try {
            val snapshot = exerciseRef.whereEqualTo("userId", idUser).get().await()
            val listExercises = snapshot.toObjects(Exercise::class.java)
            emit(Response.Success(listExercises))
        } catch (e: Exception) {
            emit(Response.Failure(e))
        }
    }

    override fun getExercisesByTrainingId(trainingId: String): Flow<Response<List<Exercise>>> =
        callbackFlow {
            val snapshotListener = exerciseRef.whereEqualTo("trainingId", trainingId)
                .addSnapshotListener { snapshot, e ->
                    val exerciseResponse = if (snapshot != null) {
                        val exercises = snapshot.toObjects(Exercise::class.java)
                        snapshot.documents.forEachIndexed { index, document ->
                            exercises[index].id = document.id
                        }
                        Response.Success(exercises)
                    } else {
                        Response.Failure(e)
                    }
                    trySend(exerciseResponse)
                }

            awaitClose { snapshotListener.remove() }
        }

    override suspend fun create(exercise: Exercise, file: File): Response<Boolean> {
        return try {
            val fromFile = Uri.fromFile(file)
            val ref = storageExerciseRef.child(file.name)
            val uploadTask = ref.putFile(fromFile).await()
            val url = ref.downloadUrl.await()

            exercise.image = url.toString()
            exerciseRef.add(exercise).await()
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun update(exercise: Exercise, file: File?): Response<Boolean> {
        return try {
            if (file != null) {
                val fromFile = Uri.fromFile(file)
                val ref = storageExerciseRef.child(file.name)
                val uploadTask = ref.putFile(fromFile).await()
                val url = ref.downloadUrl.await()
                exercise.image = url.toString()
            }

            val map: MutableMap<String, Any> = HashMap()
            map["name"] = exercise.name
            map["remarks"] = exercise.remarks
            map["image"] = exercise.image

            exerciseRef.document(exercise.id).update(map).await()
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun delete(idExercise: String): Response<Boolean> {
        return try {
            exerciseRef.document(idExercise).delete().await()
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }
    }
