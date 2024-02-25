package com.example.absolutegame.presentation.adapter.favorite

import androidx.recyclerview.widget.DiffUtil
import com.example.absolutegame.domain.Game

class FavoriteDiffUtilCallback : DiffUtil.ItemCallback<Game>() {
    override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
        return oldItem.id == newItem.id
    }
}