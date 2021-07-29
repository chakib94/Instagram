package com.taki.instagram.data.network

import com.taki.instagram.data.models.Photo
import com.taki.instagram.data.network.RemoteDataSource.Companion.Client_ID
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UserApi {

    @Headers("Accept-version: v1", "Authorization: Client-ID $Client_ID")
    @GET("/photos/random")
    suspend fun getUsers(@Query("count") count: Int): List<Photo>

}