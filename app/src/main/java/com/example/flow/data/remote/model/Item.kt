package com.example.flow.data.remote.model

import com.example.flow.model.Movie
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Item(
    val actor: String,
    val director: String,
    val image: String,
    val link: String,
    val pubDate: String,
    val subtitle: String,
    val title: String,
    val userRating: String
) {

    fun toMovie() = Movie(
        image = image,
        title = title,
        pubDate = pubDate,
        userRating = userRating,
        link = link
    )

}