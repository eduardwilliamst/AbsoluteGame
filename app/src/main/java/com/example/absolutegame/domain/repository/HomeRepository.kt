package com.example.absolutegame.domain.repository

import com.example.absolutegame.domain.Game

interface HomeRepository {
    suspend fun fetchGames(): List<Game>
}