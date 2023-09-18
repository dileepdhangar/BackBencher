package com.example.backbencher.Retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {
    private const val BASE_URL = "I have hide the API due to tokens issue with price."

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(1000, TimeUnit.SECONDS)
        .readTimeout(1000, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true) // Enable automatic retries
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}