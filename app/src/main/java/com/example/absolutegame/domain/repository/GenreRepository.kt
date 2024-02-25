package com.example.absolutegame.domain.repository

import com.example.absolutegame.domain.Genre

interface GenreRepository {
    suspend fun fetchGenres(): List<Genre>
}