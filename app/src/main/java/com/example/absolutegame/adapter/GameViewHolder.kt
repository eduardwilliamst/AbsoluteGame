package com.example.absolutegame.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.absolutegame.databinding.ItemMenuBinding
import com.example.absolutegame.domain.Game

class GameViewHolder(
    private val binding: ItemMenuBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: Game) {
        binding.tvTitleGame.text = movie.originalTitle
        binding.tvDescGame.text = movie.overview
        binding.ivGame.load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
    }
}