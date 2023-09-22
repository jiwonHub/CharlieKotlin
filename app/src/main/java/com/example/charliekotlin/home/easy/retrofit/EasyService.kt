package com.example.charliekotlin.home.easy.retrofit

import com.example.charliekotlin.home.easy.retrofit.EasyDTO
import retrofit2.Call
import retrofit2.http.GET

interface EasyService {

    @GET("/v3/292f9e25-8e13-45d1-bbf1-4816e349dac5")
    fun listQuestions(): Call<EasyDTO>
}