package io.github.duck.core

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.github.duck.core.model.Type
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RandomDuckApi {
    companion object {
        const val baseUrl = "https://random-d.uk/api/v2/"

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        private val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build().create(RandomDuckService::class.java)
    }

    suspend fun getRandomDuckJpg(): String {
        return withContext(Dispatchers.IO) {
            retrofit.getRandomResponse(Type.JPG).body()?.url ?: ""
        }
    }

    suspend fun getRandomDuckGif(): String {
        return withContext(Dispatchers.IO) {
            retrofit.getRandomResponse(Type.GIF).body()?.url ?: ""
        }
    }

    suspend fun getRandomDuck(): String {
        return withContext(Dispatchers.IO) {
            retrofit.getRandomResponse(Type.NoParam).body()?.url ?: ""
        }
    }


}
