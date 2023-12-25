package com.thiago.fitness.domain.model

data class Exercise(
    val id: Long,
    val name: String,
    var image: String = "",
    val remarks: String,
    val trainingId: Long
)