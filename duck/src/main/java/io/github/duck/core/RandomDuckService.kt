package io.github.duck.core

import io.github.duck.core.model.RandomResponse
import io.github.duck.core.model.Type
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomDuckService {
    @GET("random")
    suspend fun getRandomResponse(@Query("type") type: Type): Call<RandomResponse>

    @GET("quack")
    suspend fun getQuackResponse(@Query("type") type: Type): Call<RandomResponse>

}