package com.thiago.fitness.domain.model

import com.google.firebase.Timestamp
import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


data class Training(

    var id: String = "",
    var name: String = "",
    var description: String = "",
    val data: Timestamp = Timestamp.now(),
    var category: String = "",
    var image: String = "",
    var idUser: String = "",
    var user: User? = null,
    val exercise: Exercise? = null

    ) {
    fun toJson(): String = Gson().toJson(
        Training(
            id,
            name,
            description,
            data,
            category,
            if (image != "") URLEncoder.encode(image, StandardCharsets.UTF_8.toString()) else "",
            idUser,
            User(
                id = user?.id ?: "",
                username = user?.username ?: "",
                email = user?.email ?: "",
                image =
                if (!user?.image.isNullOrBlank())
                    URLEncoder.encode(user?.image, StandardCharsets.UTF_8.toString())
                else "",
            ),
            exercise

            )
    )

    companion object {
        fun fromJson(data: String): Training = Gson().fromJson(data, Training::class.java)
    }


}