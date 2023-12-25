package com.thiago.fitness.domain.model


data class Exercise(
    var id: String = "",
    val name: String = "",
    val remarks: String = "",
    var image: String = "",
    val trainingId: String = ""
)
