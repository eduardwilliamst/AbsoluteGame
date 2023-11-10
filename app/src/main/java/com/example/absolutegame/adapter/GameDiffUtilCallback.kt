package com.example.absolutegame.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.absolutegame.domain.Game

class GameDiffUtilCallback : DiffUtil.ItemCallback<Game>() {
    override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
        return oldItem.id == newItem.id
    }
}