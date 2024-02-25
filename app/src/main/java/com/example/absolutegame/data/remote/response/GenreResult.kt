package com.example.absolutegame.data.remote.response

import com.example.absolutegame.domain.Game
import com.example.absolutegame.domain.Genre
import com.google.gson.annotations.SerializedName

data class GenreResult(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("games_count")
    val gamesCount: Int?,
    @SerializedName("image_background")
    val imageBackground: String?
)

fun GenreResult.toGenre(): Genre {
    return Genre(
        name = name.orEmpty(),
        id = id ?: -1,
        slug = slug.orEmpty(),
        gamesCount = gamesCount ?: -1,
        imageBackground = imageBackground.orEmpty()
    )
}