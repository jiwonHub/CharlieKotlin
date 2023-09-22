package com.example.charliekotlin.home.normal.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface NormalService {

    @GET("/v3/2dba26bf-a2cc-46b8-bb35-ef978f974977")
    fun listQuestions(): Call<NormalDTO>
}