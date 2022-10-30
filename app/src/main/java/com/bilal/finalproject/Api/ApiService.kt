package com.bilal.finalproject.Api

import retrofit2.Call
import com.bilal.finalproject.ResponseMovie
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    fun getMovie(@Query("api_key")apiKey:String) : Call<ResponseMovie>
}

