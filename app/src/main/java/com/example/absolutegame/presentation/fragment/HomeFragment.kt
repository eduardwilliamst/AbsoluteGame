package com.example.absolutegame.presentation.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.absolutegame.Application
import com.example.absolutegame.R
import com.example.absolutegame.adapter.GameAdapter
import com.example.absolutegame.databinding.FragmentHomeBinding
import com.example.absolutegame.di.ViewModelFactory
import com.example.absolutegame.domain.Game
import com.example.absolutegame.presentation.home.HomeViewModel
import com.example.absolutegame.presentation.profile.ProfileActivity

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private var gameAdapter: GameAdapter? = null

    private val viewModel by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance((requireActivity().application as Application).provider)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeLiveData()
        setupGameAdapter()

        viewModel.fetchGames()
    }

    private fun setupGameAdapter() {
        gameAdapter = GameAdapter()
        binding?.recyclerGame?.adapter = gameAdapter
        binding?.recyclerGame?.layoutManager = LinearLayoutManager(requireContext())
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