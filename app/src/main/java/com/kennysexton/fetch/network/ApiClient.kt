package com.kennysexton.fetch.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://fetch-hiring.s3.amazonaws.com/"

private val okHttpClient = OkHttpClient.Builder().addInterceptor(
    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
).build()

object RetrofitClient {
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
}

object ApiClient {
    val apiService: FetchInterface by lazy {
        RetrofitClient.retrofit.create(FetchInterface::class.java)
    }
}