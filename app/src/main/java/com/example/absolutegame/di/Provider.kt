package com.example.absolutegame.di

import android.content.Context
import com.example.absolutegame.data.DataStoreManager
import com.example.absolutegame.data.LocalRepository
import com.example.absolutegame.data.remote.RemoteRepository
import com.example.absolutegame.data.remote.service.GameService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Provider(
    private val context: Context,
) {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://www.freetogame.com/api/games")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val gameService: GameService = retrofit.create(GameService::class.java)

    private val dataStoreManager = DataStoreManager(context)

    val localRepository: LocalRepository = LocalRepository(
        dataStoreManager = dataStoreManager,
    )

    val remoteRepository: RemoteRepository = RemoteRepository(
        gamesService = gameService,
    )
}