package com.example.newmonage.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface MonageApi {
    @GET("/rest/v1/Monage App?select=*")
    suspend fun get(
        @Header("Authorization") token: String,
        @Header("apikey") apiKey: String
    ): Response<List<MonageItem>>

    @POST("/rest/v1/Monage App?insert")
    suspend fun create(
        @Header("Authorization") token: String,
        @Header("apikey") apiKey: String,
        @Body monageData: MonageData
    )
}

