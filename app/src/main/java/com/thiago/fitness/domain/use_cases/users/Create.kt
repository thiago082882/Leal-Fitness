package com.thiago.fitness.domain.use_cases.users

import com.thiago.fitness.domain.model.User
import com.thiago.fitness.domain.repository.UsersRepository
import javax.inject.Inject

class Create @Inject constructor(private val repository: UsersRepository) {

    suspend operator fun invoke(user: User) = repository.create(user)
}