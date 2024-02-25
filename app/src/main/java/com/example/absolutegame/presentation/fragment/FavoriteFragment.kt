package com.example.absolutegame.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.absolutegame.Application
import com.example.absolutegame.databinding.FragmentFavoriteBinding
import com.example.absolutegame.di.ViewModelFactory
import com.example.absolutegame.domain.Game
import com.example.absolutegame.presentation.adapter.favorite.FavoriteAdapter
import com.example.absolutegame.presentation.favorite.FavoriteViewModel

class   FavoriteFragment : Fragment() {

    private var binding: FragmentFavoriteBinding? = null
    private var gameAdapter: FavoriteAdapter? = null

    private val viewModel by viewModels<FavoriteViewModel> {
        ViewModelFactory.getInstance((requireActivity().application as Application).provider, requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeLiveData()
        setupGameAdapter()

        viewModel.loadFavoriteGames()

    }

    private fun setupGameAdapter() {
        gameAdapter = FavoriteAdapter()
        binding?.recyclerFavorite?.adapter = gameAdapter
        binding?.recyclerFavorite?.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun observeLiveData() {
        viewModel.loading.observe(viewLifecycleOwner, ::handleLoading)
        viewModel.error.observe(viewLifecycleOwner, ::handleError)
        viewModel.games.observe(viewLifecycleOwner, ::handleGames)
    }

    private fun handleLoading(isLoading: Boolean) {
        binding?.progress?.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun handleError(error: String?) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
    }

    private fun handleGames(games: List<Game>) {
        gameAdapter?.submitList(games)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}