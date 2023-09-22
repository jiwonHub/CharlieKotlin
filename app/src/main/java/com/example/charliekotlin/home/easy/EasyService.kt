package com.example.charliekotlin.home.easy

import retrofit2.Call
import retrofit2.http.GET

interface EasyService {

    @GET("/v3/aebc1190-53a4-4b5d-abf3-8e03b9003412")
    fun listQuestions(): Call<EasyDTO>
}