package com.example.newmonage.api

import retrofit2.Response
import retrofit2.http.*

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
    @PATCH("/rest/v1/Monage App")
    suspend fun update(
        @Header("Authorization") token: String,
        @Header("apikey") apiKey: String,
        @Query("id") idQuery : String,
        @Body monageData: MonageData
    ) : Response<Unit>

    @DELETE("/rest/v1/Monage App")
    suspend fun delete(
        @Header("Authorization") token: String,
        @Header("apikey") apiKey: String,
        @Query("id") idQuery : String
    ) : Response<Unit>
}

