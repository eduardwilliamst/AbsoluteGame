package com.example.absolutegame.data.remote

import com.example.absolutegame.data.remote.response.toGame
import com.example.absolutegame.data.remote.service.GameService
import com.example.absolutegame.domain.Game
import com.example.absolutegame.domain.repository.HomeRepository

class RemoteRepository (
    private val gamesService: GameService,
) : HomeRepository {
    override suspend fun fetchGames(): List<Game> {
        return gamesService.fetchGames().results?.map { result -> result.toGame() }.orEmpty()
    }
}