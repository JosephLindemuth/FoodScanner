package com.example.foodscanner.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

private const val placeholderUrl = "http://localhost"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(placeholderUrl)
    .build()

interface BarcodeApiService {
    @GET
    suspend fun getInfo(@Url url: String?): String?
}

object BarcodeApi {
    val retrofitService: BarcodeApiService by lazy {
        retrofit.create(BarcodeApiService::class.java)
    }
}