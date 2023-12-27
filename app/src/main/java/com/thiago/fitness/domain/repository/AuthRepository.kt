package com.thiago.fitness.domain.repository

import com.google.firebase.auth.FirebaseUser
import com.thiago.fitness.domain.model.Response
import com.thiago.fitness.domain.model.User

interface AuthRepository {

    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Response<FirebaseUser>
    suspend fun signUp(user: User): Response<FirebaseUser>
    fun logout()

}