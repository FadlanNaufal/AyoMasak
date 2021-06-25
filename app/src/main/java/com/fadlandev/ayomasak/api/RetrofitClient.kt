package com.fadlandev.ayomasak.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val BASE_URL = "http://192.168.100.17/AyoMasak/public/api/"

    val instance : Api by lazy{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    retrofit.create(Api::class.java)
    }
}