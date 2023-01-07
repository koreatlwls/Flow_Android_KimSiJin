package com.example.flow.data.remote.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorResponse(
    val errorMessage: String,
    val errorCode: String,
)