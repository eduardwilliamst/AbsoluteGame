package com.example.absolutegame.presentation.adapter.game

import android.content.Context
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.absolutegame.databinding.ItemMenuBinding
import com.example.absolutegame.domain.Game
import com.example.absolutegame.presentation.home.HomeViewModel

class GameViewHolder(
    private val binding: ItemMenuBinding,
    private val homeViewModel: HomeViewModel,
    private val context: Context
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(game: Game) {
        binding.tvTitleGame.text = game.name
        binding.tvDescGame.text = game.description
        binding.ivGame.load(game.imageBackground)

        binding.btnFavorite.setOnClickListener {
            homeViewModel.saveFavorite(game)
            showFavoriteAddedMessage(game.name) // Menampilkan pesan saat game ditambahkan ke favorit
        }
    }

    private fun showFavoriteAddedMessage(gameName: String) {
        val message = "$gameName telah ditambahkan ke favorit"
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}