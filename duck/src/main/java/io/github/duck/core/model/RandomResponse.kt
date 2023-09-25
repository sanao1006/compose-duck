package io.github.duck.core.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RandomResponse(
    @Json(name = "message")
    val message: String,
    @Json(name = "url")
    val url: String
)