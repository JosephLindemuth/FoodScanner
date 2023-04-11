package com.example.foodscanner.network

//import com.squareup.moshi.Json
//import com.squareup.moshi.Moshi
//import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
//import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

//return fetch("https://world.openfoodfacts.org/api/v0/product/" + upc + ".json") // Query UPC API

private const val BASE_URL = "https://world.openfoodfacts.org/api/v0/product/070847811169.json/"

//private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com"


//private val moshi = Moshi.Builder()
//    .add(KotlinJsonAdapterFactory())
//    .build()

//private val retrofit = Retrofit.Builder()

// makes it into a string?
//private val retrofit = Retrofit.Builder().addConverterFactory(ScalarsConverterFactory.create())

// base url for start point
//private val retrofit = Retrofit.Builder()
//    .addConverterFactory(MoshiConverterFactory.create(moshi))
//    .baseUrl(BASE_URL)
//    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface BarcodeApiService {
    @GET("info")
    suspend fun getInfo(): String
}

object BarcodeApi {
    val retrofitService: BarcodeApiService by lazy {
        retrofit.create(BarcodeApiService::class.java)
    }
}