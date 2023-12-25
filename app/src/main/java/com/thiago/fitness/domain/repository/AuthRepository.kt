package com.thiago.fitness.domain.repository

import com.google.firebase.auth.FirebaseUser
import com.thiago.fitness.domain.model.Response
import com.thiago.fitness.domain.model.User

interface AuthRepository {

    val currentUser: FirebaseUser?
    suspend fun login(email: String, senha: String): Response<FirebaseUser>
    suspend fun sigUp(user : User): Response<FirebaseUser>
    fun logout ()

}