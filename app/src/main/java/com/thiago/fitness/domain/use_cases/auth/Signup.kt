package com.thiago.fitness.domain.use_cases.auth

import com.thiago.fitness.domain.model.User
import com.thiago.fitness.domain.repository.AuthRepository
import javax.inject.Inject

class Signup  @Inject constructor(private val repository: AuthRepository) {

    suspend operator fun invoke(user: User) = repository.sigUp(user)
}