package com.example.absolutegame.data.remote.service

import com.example.absolutegame.data.remote.response.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GameService {

    @GET("discover/movie")
    suspend fun fetchGames(
        @Query("api_key") apiKey: String = "4b9bfb0e83de2a4afb17c157ccb254f3",
        @Query("with_original_language") withOriginalLanguage: String = "ko",
    ): Response
}