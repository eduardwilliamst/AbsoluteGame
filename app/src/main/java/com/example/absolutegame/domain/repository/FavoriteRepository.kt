package com.example.absolutegame.domain.repository

import com.example.absolutegame.domain.Game

interface FavoriteRepository {
    suspend fun getFavorites(): List<Game>

}