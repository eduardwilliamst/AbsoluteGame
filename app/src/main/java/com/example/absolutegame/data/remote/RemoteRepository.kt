package com.example.absolutegame.data.remote

import com.example.absolutegame.data.remote.response.toGame
import com.example.absolutegame.data.remote.response.toGenre
import com.example.absolutegame.data.remote.service.GameService
import com.example.absolutegame.domain.Game
import com.example.absolutegame.domain.Genre
import com.example.absolutegame.domain.repository.GenreRepository
import com.example.absolutegame.domain.repository.HomeRepository

class RemoteRepository (
    private val gamesService: GameService,
) : HomeRepository,GenreRepository {
    override suspend fun fetchGames(): List<Game> {
        return gamesService.fetchGames().results?.map { result -> result.toGame() }.orEmpty()
    }

    override suspend fun fetchGenres(): List<Genre> {
        return gamesService.fetchGenres().results?.map { result -> result.toGenre() }.orEmpty()
    }
}