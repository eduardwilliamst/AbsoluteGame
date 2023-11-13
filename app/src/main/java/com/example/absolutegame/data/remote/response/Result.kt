package com.example.absolutegame.data.remote.response

import com.example.absolutegame.domain.Game
import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("thumbnail")
    val thumbnail: String?,
    @SerializedName("short_description")
    val shortDescription: String?,
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
    val link: String?
)

fun Result.toGame(): Game {
    return Game(
        genre = genre.orEmpty(),
        title = title.orEmpty(),
        thumbnail = thumbnail.orEmpty(),
        id = id ?: -1,
        shortDescription = shortDescription.orEmpty(),
    )
}