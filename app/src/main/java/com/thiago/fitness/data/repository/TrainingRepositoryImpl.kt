package com.thiago.fitness.data.repository

import android.net.Uri
import android.util.Log
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.storage.StorageReference
import com.thiago.fitness.core.Constants.TRAINING
import com.thiago.fitness.core.Constants.USERS
import com.thiago.fitness.domain.model.Response
import com.thiago.fitness.domain.model.Training
import com.thiago.fitness.domain.model.User
import com.thiago.fitness.domain.repository.TrainingRepository
import kotlinx.coroutines.DelicateCoroutinesApi

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject
import javax.inject.Named

class TrainingRepositoryImpl @Inject constructor(

    @Named(TRAINING) private val trainingRef: CollectionReference,
    @Named(USERS) private val usersRef: CollectionReference,
    @Named(TRAINING) private val storageTrainingRef: StorageReference,

    ) : TrainingRepository {

    override fun getTraining(): Flow<Response<List<Training>>> = callbackFlow {

        val snapshotListener = trainingRef.addSnapshotListener { snapshot, e ->


            GlobalScope.launch(Dispatchers.IO) {
                val trainingResponse = if (snapshot != null) {
                    val training = snapshot.toObjects(Training::class.java)

                    snapshot.documents.forEachIndexed { index, document ->
                        training[index].id = document.id
                    }

                    val idUserArray = ArrayList<String>()

                    training.forEach { training ->
                        idUserArray.add(training.idUser)
                    }

                    val idUserList = idUserArray.toSet().toList()

                    idUserList.map { id ->
                        async {
                            val user = usersRef.document(id).get().await().toObject(User::class.java)!!
                            training.forEach { training ->
                                if (training.idUser == id) {
                                    training.user = user
                                }
                            }

                            Log.d("TrainingRepositoryImpl", "Id: ${id}")
                        }
                    }.forEach {
                        it.await()
                    }

                    Response.Success(training)
                }
                else {
                    Response.Failure(e)
                }
                trySend(trainingResponse)
            }

        }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override fun getTrainingByUserId(idUser: String): Flow<Response<List<Training>>> = callbackFlow {
        val snapshotListener =
            trainingRef.whereEqualTo("idUser", idUser).addSnapshotListener { snapshot, e ->


                val trainingResponse = if (snapshot != null) {
                    val listTraining = snapshot.toObjects(Training::class.java)
                    snapshot.documents.forEachIndexed{
                        index,document ->
                        listTraining[index].id = document.id

                    }


                    Response.Success(listTraining)
                } else {
                    Response.Failure(e)

                }
                trySend(trainingResponse)
            }



        awaitClose {
            snapshotListener.remove()
        }
    }

    override suspend fun create(training: Training, file: File): Response<Boolean> {

        return try {
            //IMAGE
            val fromFile = Uri.fromFile(file)
            val ref = storageTrainingRef.child(file.name)
            val uploadTask = ref.putFile(fromFile).await()
            val url = ref.downloadUrl.await()

            //DATA
            training.image = url.toString()
            trainingRef.add(training).await()
            Response.Success(true)


        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }
    }
    override suspend fun update(training: Training, file: File?): Response<Boolean> {
        return try {
            // IMAGE
            if (file != null) {
                val fromFile = Uri.fromFile(file)
                val ref = storageTrainingRef.child(file.name)
                val uploadTask = ref.putFile(fromFile).await()
                val url = ref.downloadUrl.await()
                training.image = url.toString()
            }
            val map: MutableMap<String, Any> = HashMap()
            map["name"] = training.name
            map["description"] = training.description
            map["image"] = training.image
            map["category"] = training.category
            map["data"]= training.data
            // DATA
            trainingRef.document(training.id).update(map).await()
            Response.Success(true)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override suspend fun delete(idTraining: String): Response<Boolean> {

        return try {
            trainingRef.document(idTraining).delete().await()
            Response.Success(true)

        }catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }

    }

}