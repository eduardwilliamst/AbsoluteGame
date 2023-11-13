package com.example.absolutegame.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.absolutegame.databinding.ItemMenuBinding
import com.example.absolutegame.domain.Game

class GameViewHolder(
    private val binding: ItemMenuBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(game: Game) {
        binding.tvTitleGame.text = game.title
        binding.tvDescGame.text = game.shortDescription
    }
}