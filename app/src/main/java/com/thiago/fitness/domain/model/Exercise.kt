package com.thiago.fitness.domain.model

data class Exercise(
    var id: String = "",
    val name: String = "",
    var image: String = "",
    val remarks: String = "",
    var training: Training? = null,
    val trainingId: String
)