package com.example.fbimostwanted.net

import com.example.fbimostwanted.PersonItem
import com.example.fbimostwanted.WantedList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface WantedSource {

    @Headers(
        "Content-Type: application/json",
        "User-Agent: AndroidTestApp",
    )
    @GET("list")
    suspend fun getAllWanted() : WantedList

    @Headers(
        "Content-Type: application/json",
        "User-Agent: AndroidTestApp",
    )
    @GET("list")
    suspend fun getWantedPage(@Query("page") pageNumber: Int): WantedList

}