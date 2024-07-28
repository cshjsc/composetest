package com.example.fbimostwanted.net

import com.example.fbimostwanted.WantedList
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class RemoteWantedSource {

    private val retrofit = getClient()
    private val userApi = retrofit.create(WantedSource::class.java)

    suspend fun getAllWanted(): WantedList=
        userApi.getAllWanted()

        //return res.body()?.takeIf {
        //    res.isSuccessful
        //} ?: throw Exception("Failed to fetch data")


    suspend fun getWantedPage(pageNumber: Int): WantedList =
         userApi.getWantedPage(pageNumber)

    //val res = userApi.getWantedPage(pageNumber).execute()
    //    return res.body()?.takeIf {
    //        res.isSuccessful
    //    } ?: throw Exception("Failed to fetch data")
    //}


    companion object RetrofitClient {
        private const val BASE_URL = "https://api.fbi.gov/wanted/v1/"
        fun getClient(): Retrofit =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
    }

}