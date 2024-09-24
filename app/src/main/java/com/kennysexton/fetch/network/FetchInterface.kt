package com.kennysexton.fetch.network

import com.kennysexton.fetch.modal.FetchData
import retrofit2.Response
import retrofit2.http.GET

interface FetchInterface {

    @GET("hiring.json")
    suspend fun getFetchData(): Response<List<FetchData>>

}