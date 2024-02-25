package com.example.absolutegame.data.remote.service

import com.example.absolutegame.data.remote.response.GenreResponse
import com.example.absolutegame.data.remote.response.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GameService {

    @GET("games")
    suspend fun fetchGames(
        @Query("key") apiKey: String = "9164f0263dc545f496c0bd00ba00b08c",
    ): Response

    @GET("genres")
    suspend fun fetchGenres(
        @Query("key") apiKey: String = "9164f0263dc545f496c0bd00ba00b08c",
    ): GenreResponse
}