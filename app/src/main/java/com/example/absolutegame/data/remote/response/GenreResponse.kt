package com.example.absolutegame.data.remote.response

import com.google.gson.annotations.SerializedName

data class GenreResponse(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: List<GenreResult>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)