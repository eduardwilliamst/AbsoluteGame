package com.example.absolutegame.data.remote.service

import com.example.absolutegame.data.remote.response.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GameService {

    @GET("discover/game")
    suspend fun fetchGames(
        @Query("X-RapidAPI-Key") apiKey: String = "4b9bfb0e83de2a4afb17c157ccb254f3",
    ): Response
}