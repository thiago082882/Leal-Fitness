package com.thiago.fitness.domain.use_cases.auth


import com.thiago.fitness.domain.repository.AuthRepository
import javax.inject.Inject

class Logout @Inject constructor(private val repository: AuthRepository) {

     operator  fun invoke() = repository.logout()


     }

