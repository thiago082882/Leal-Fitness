package com.thiago.fitness.domain.use_cases.users

import com.thiago.fitness.domain.model.User
import com.thiago.fitness.domain.repository.UsersRepository
import javax.inject.Inject

class Update @Inject constructor(private val repository: UsersRepository) {

    suspend operator fun invoke(user : User) = repository.update(user)
}