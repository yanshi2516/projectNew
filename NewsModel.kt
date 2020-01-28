package com.example.projectnews.newstories

import kotlinx.serialization.Serializable

    @Serializable
    data class News(
        val by: String,
        val descendants: String,
        val id: Int,
        val kids: List<Int>? = null,
        val score: Int,
        val time: Long,
        val title: String,
        val type: String,
        val url: String? = null
    )