package com.example.absolutegame.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.absolutegame.databinding.ItemMenuBinding
import com.example.absolutegame.domain.Game

class GameAdapter: ListAdapter<Game, GameViewHolder>(GameDiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        return GameViewHolder(
            ItemMenuBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}