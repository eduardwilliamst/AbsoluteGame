package com.example.absolutegame.presentation.adapter.favorite

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.absolutegame.databinding.ItemGenreBinding
import com.example.absolutegame.databinding.ItemMenuBinding
import com.example.absolutegame.domain.Game

class FavoriteViewHolder(
    private val binding: ItemMenuBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(favorite: Game) {
        binding.tvTitleGame.text = favorite.name
        binding.ivGame.load(favorite.imageBackground)

    }
}