package com.example.absolutegame.domain

data class Game (
    val id: Int,
    val backdropPath: String,
    val originalTitle: String,
    val overview: String,
    val posterPath: String,
)