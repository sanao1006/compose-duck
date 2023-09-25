package io.github.duck.core

import io.github.duck.core.model.RandomResponse
import io.github.duck.core.model.Type
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomDuckService {
    @GET("random")
    suspend fun getRandomResponse(@Query("type") type: Type): Response<RandomResponse>

}