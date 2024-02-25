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
import com.example.absolutegame.databinding.FragmentGenreBinding
import com.example.absolutegame.di.ViewModelFactory
import com.example.absolutegame.domain.Genre
import com.example.absolutegame.presentation.adapter.genre.GenreAdapter
import com.example.absolutegame.presentation.genre.GenreViewModel

class GenreFragment : Fragment() {

    private var binding: FragmentGenreBinding? = null
    private var genreAdapter: GenreAdapter? = null

    private val viewModel by viewModels<GenreViewModel> {
        ViewModelFactory.getInstance((requireActivity().application as Application).provider, requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGenreBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeLiveData()
        setupGenreAdapter()

        viewModel.fetchGenres()
    }

    private fun setupGenreAdapter() {
        genreAdapter = GenreAdapter()
        binding?.recyclerGenre?.adapter = genreAdapter
        binding?.recyclerGenre?.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun observeLiveData() {
        viewModel.loading.observe(viewLifecycleOwner, ::handleLoading)
        viewModel.error.observe(viewLifecycleOwner, ::handleError)
        viewModel.genres.observe(viewLifecycleOwner, ::handleGenres)
    }

    private fun handleLoading(isLoading: Boolean) {
        binding?.progress?.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun handleError(error: String?) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
    }

    private fun handleGenres(genres: List<Genre>) {
        genreAdapter?.submitList(genres)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}