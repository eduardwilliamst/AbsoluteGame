package com.example.absolutegame.presentation.adapter.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.absolutegame.databinding.ItemGenreBinding
import com.example.absolutegame.databinding.ItemMenuBinding
import com.example.absolutegame.domain.Game

class FavoriteAdapter: ListAdapter<Game, FavoriteViewHolder>(FavoriteDiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            ItemMenuBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}