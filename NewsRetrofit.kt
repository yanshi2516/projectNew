package com.example.projectnews.newstories

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit

@UnstableDefault
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://hacker-news.firebaseio.com/v0/")
        .addConverterFactory(Json.nonstrict.asConverterFactory(MediaType.get("application/json")))
        .build()
