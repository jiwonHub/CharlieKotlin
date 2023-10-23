package com.example.charliekotlin.home.easy.retrofit

import com.example.charliekotlin.home.easy.retrofit.EasyDTO
import retrofit2.Call
import retrofit2.http.GET

interface EasyService {

    @GET("/v3/8f0c50b3-37e4-423e-b920-01707c9ba744")
    fun listQuestions(): Call<EasyDTO>
}