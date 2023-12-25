package com.thiago.fitness.screens.detail_training

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.thiago.fitness.domain.model.Training
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DetailTrainingViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle

): ViewModel() {

    val data = savedStateHandle.get<String>("training")
    val training = Training.fromJson(data!!)
}