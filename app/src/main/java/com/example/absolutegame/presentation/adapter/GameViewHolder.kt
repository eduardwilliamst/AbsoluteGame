package com.example.absolutegame.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.absolutegame.databinding.ItemMenuBinding
import com.example.absolutegame.domain.Game

class GameViewHolder(
    private val binding: ItemMenuBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(game: Game) {
        binding.tvTitleGame.text = game.name
        binding.tvDescGame.text = game.description
        binding.ivGame.load(game.imageBackground)
    }
}