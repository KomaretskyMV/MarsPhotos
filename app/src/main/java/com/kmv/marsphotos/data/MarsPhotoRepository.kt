package com.kmv.marsphotos.data

import com.kmv.marsphotos.entity.Photo
import kotlinx.coroutines.delay
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

class MarsPhotoRepository @Inject constructor() {
    suspend fun getMarsPhotos(earth_date: String, page: Int) : List<Photo> {
        delay(2000)
        return RetrofitServices.marsPhotosApi.getMarsPhotos(API_KEY, earth_date, page).photos
    }
}

private const val BASE_URL = "https://api.nasa.gov"
private const val API_KEY = "9ON9YMTHm2jVyFiZ7xW6tHZ00FZ2D8VNve6GmQkl"

object RetrofitServices {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val marsPhotosApi: MarsPhotosApi = retrofit.create(
        MarsPhotosApi::class.java
    )
}

interface MarsPhotosApi {
    @GET("/mars-photos/api/v1/rovers/curiosity/photos")
    suspend fun getMarsPhotos(
        @Query("api_key") apiKey: String,
        @Query("earth_date") earth_date: String,
        @Query("page") page: Int? = null
    ) : MarsPhotoDtoList
}