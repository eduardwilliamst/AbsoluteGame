package com.example.absolutegame.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.absolutegame.domain.Game
import com.example.absolutegame.domain.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val homeRepository: HomeRepository,
) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val _movies = MutableLiveData<List<Game>>()
    val movies: LiveData<List<Game>> = _movies

    fun fetchMovies() {
        _loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                homeRepository.fetchGames()
            }.onFailure { exception ->
                withContext(Dispatchers.Main) {
                    _loading.value = false
                    _error.value = exception.message
                }
            }.onSuccess { movies ->
                withContext(Dispatchers.Main) {
                    _loading.value = false
                    _movies.value = movies
                }
            }
        }
    }
}