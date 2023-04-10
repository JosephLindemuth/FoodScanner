package com.example.foodscanner.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com"

//private val retrofit = Retrofit.Builder()

// makes it into a string?
//private val retrofit = Retrofit.Builder().addConverterFactory(ScalarsConverterFactory.create())

// base url for start point
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface BarcodeApiService {
    @GET("photos")
    suspend fun getPhotos(): String
}

object BarcodeApi {
    val retrofitService : BarcodeApiService by lazy {
        retrofit.create(BarcodeApiService::class.java)
    }
}
