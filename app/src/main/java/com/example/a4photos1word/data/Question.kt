package com.example.a4photos1word.data

import androidx.annotation.DrawableRes

data class Question(

    val id: Int,
    @DrawableRes val images: List<Int>,
    val answer: String
)
