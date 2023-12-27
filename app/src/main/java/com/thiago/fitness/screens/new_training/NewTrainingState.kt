package com.thiago.fitness.screens.new_training

import com.google.firebase.Timestamp

data class NewTrainingState(
    val image: String = "",
    val name: String = "",
    val description: String = "",
    val data: Timestamp = Timestamp.now(),
    val category: String = "",
)