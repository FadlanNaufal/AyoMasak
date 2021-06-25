package com.fadlandev.ayomasak.model

data class Recipe (
    val id: Int,
    val recipe_title: String?,
    val recipe_desc: String?,
    val recipe_image: String?,
    val recipe_portion: String?,
    val recipe_time: String?,
    val recipe_ingredient: String?
)
