package com.example.absolutegame.presentation.genre

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.absolutegame.domain.Genre
import com.example.absolutegame.domain.repository.GenreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GenreViewModel(
    private val genreRepository: GenreRepository,
) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val _genres = MutableLiveData<List<Genre>>()
    val genres: LiveData<List<Genre>> = _genres

    fun fetchGenres() {
        _loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                genreRepository.fetchGenres()
            }.onFailure { exception ->
                withContext(Dispatchers.Main) {
                    _loading.value = false
                    _error.value = exception.message
                }
            }.onSuccess { genres ->
                withContext(Dispatchers.Main) {
                    _loading.value = false
                    _genres.value = genres
                }
            }
        }
    }
}