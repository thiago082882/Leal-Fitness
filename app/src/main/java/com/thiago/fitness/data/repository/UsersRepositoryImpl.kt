package com.thiago.fitness.data.repository


import android.net.Uri
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.storage.StorageReference
import com.thiago.fitness.core.Constants
import com.thiago.fitness.domain.model.Response
import com.thiago.fitness.domain.model.User
import com.thiago.fitness.domain.repository.UsersRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject
import javax.inject.Named

class UsersRepositoryImpl @Inject constructor(
    @Named(Constants.USERS) private val usersRef: CollectionReference,
    @Named(Constants.USERS) private val storageUsersRef: StorageReference,

    ) : UsersRepository {

    override suspend fun create(user: User): Response<Boolean> {

        return try {
            user.password = ""
            usersRef.document(user.id).set(user).await()
            Response.Success(true)

        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(e)

        }

    }

    override suspend fun update(user: User): Response<Boolean> {
        return try {
            val map: MutableMap<String, Any> = HashMap()
            map["username"] = user.username
            map["image"] = user.image
            usersRef.document(user.id).update(map).await()
            Response.Success(true)

        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(e)

        }
    }

    override suspend fun saveImage(file: File): Response<String> {
        return try {
            val fromFile = Uri.fromFile(file)
            val ref = storageUsersRef.child(file.name)
            val uploadTask = ref.putFile(fromFile).await()
            val url = ref.downloadUrl.await()
            return Response.Success(url.toString())

        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override fun getUserById(id: String): Flow<User> = callbackFlow {
        val snapshotListener = usersRef.document(id).addSnapshotListener { snapshot, e ->
            val user = snapshot?.toObject(User::class.java) ?: User()
            trySend(user)


        }
        awaitClose {
            snapshotListener.remove()
        }
    }
}