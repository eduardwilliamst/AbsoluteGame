package com.example.absolutegame.presentation.adapter.genre

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.absolutegame.databinding.ItemGenreBinding
import com.example.absolutegame.domain.Genre

class GenreAdapter: ListAdapter<Genre, GenreViewHolder>(GenreDiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        return GenreViewHolder(
            ItemGenreBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}