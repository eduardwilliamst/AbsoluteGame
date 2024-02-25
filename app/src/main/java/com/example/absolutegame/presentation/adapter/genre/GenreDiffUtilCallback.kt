package com.example.absolutegame.presentation.adapter.genre

import androidx.recyclerview.widget.DiffUtil
import com.example.absolutegame.domain.Genre

class GenreDiffUtilCallback : DiffUtil.ItemCallback<Genre>() {
    override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean {
        return oldItem.id == newItem.id
    }
}