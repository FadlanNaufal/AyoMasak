package com.fadlandev.ayomasak.api

import com.fadlandev.ayomasak.response.RecipeResponse
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("get-data")
    fun getRecipe(): Call<RecipeResponse>
}