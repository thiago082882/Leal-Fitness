package com.thiago.fitness.presentation.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import com.google.firebase.Timestamp

fun formatTimestamp(timestamp: Timestamp): String {
    val date: Date = timestamp.toDate()
    val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
    return formatter.format(date)
}
