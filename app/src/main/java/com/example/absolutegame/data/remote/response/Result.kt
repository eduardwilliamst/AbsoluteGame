package com.example.absolutegame.data.remote.response

import com.example.absolutegame.domain.Game
import com.example.absolutegame.domain.Genre
import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("background_image")
    val image: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("game_url")
    val gameUrl: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("genre")
    val genre: String?,
    @SerializedName("platform")
    val platform: String?,
    @SerializedName("publisher")
    val publisher: String?,
    @SerializedName("developer")
    val developer: String?,
    @SerializedName("freetogame_profile_url")
    val link: String?,
)

fun Result.toGame(): Game {
    return Game(
        genre = genre.orEmpty(),
        name = name.orEmpty(),
        imageBackground = image.orEmpty(),
        id = id ?: -1,
        description = description.orEmpty(),
    )
}