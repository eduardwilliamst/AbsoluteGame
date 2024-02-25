package com.example.absolutegame.presentation.adapter.game

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.absolutegame.databinding.ItemMenuBinding
import com.example.absolutegame.domain.Game
import com.example.absolutegame.presentation.home.HomeViewModel

class GameAdapter(
    private val context: Context, private val homeViewModel: HomeViewModel) : ListAdapter<Game, GameViewHolder>(GameDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val binding = ItemMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameViewHolder(binding, homeViewModel, context)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
