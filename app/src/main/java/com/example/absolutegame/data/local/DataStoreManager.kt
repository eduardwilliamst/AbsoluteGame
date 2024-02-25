package com.example.absolutegame.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.absolutegame.domain.Game
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class DataStoreManager(
    private val context: Context
) {

    companion object {
        private val KEY_TOKEN = stringPreferencesKey("token")
        private val KEY_USER_NAME = stringPreferencesKey("username")
        private val KEY_EMAIL = stringPreferencesKey("email")
        private val KEY_FAVORITES = stringPreferencesKey("favorites")
        private const val PREF_NAME = "sharedPref"

        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREF_NAME)
    }

    suspend fun saveToken(token: String) {
        context.dataStore.edit { settings -> settings[KEY_TOKEN] = token }
    }

    suspend fun loadToken(): Flow<String?> {
        return context.dataStore.data.map { preferences -> preferences[KEY_TOKEN] }
    }

    suspend fun saveUsername(username: String) {
        context.dataStore.edit { settings -> settings[KEY_USER_NAME] = username }
    }

    suspend fun loadUsername(): Flow<String?> {
        return context.dataStore.data.map { preferences -> preferences[KEY_USER_NAME] }
    }

    suspend fun saveEmail(email: String) {
        context.dataStore.edit { settings -> settings[KEY_EMAIL] = email }
    }

    suspend fun loadEmail(): Flow<String?> {
        return context.dataStore.data.map { preferences -> preferences[KEY_EMAIL] }
    }

    suspend fun deleteUsername() {
        context.dataStore.edit { settings -> settings.remove(KEY_USER_NAME) }
    }

    suspend fun deleteEmail() {
        context.dataStore.edit { settings -> settings.remove(KEY_EMAIL) }
    }

    suspend fun deleteToken() {
        context.dataStore.edit { settings -> settings.remove(KEY_TOKEN) }
    }

    suspend fun saveFavorite(game: Game) {
        context.dataStore.edit { preferences ->
            val favoritesString: String? = preferences[KEY_FAVORITES]
            val favorites: MutableSet<String> = favoritesString?.split(",")?.toMutableSet() ?: mutableSetOf()
            favorites.add(game.id.toString())
            preferences[KEY_FAVORITES] = favorites.joinToString(",")
        }
    }

    suspend fun getFavorites(): List<Game> {
        return context.dataStore.data.map { preferences ->
            val favoritesString: String? = preferences[KEY_FAVORITES]
            val favoriteGames = favoritesString?.split(",")?.mapNotNull { favoriteString ->
                val gameInfo = favoriteString.split(",")
                if (gameInfo.size == 3) {
                    val gameId = gameInfo[0].toInt()
                    val name = gameInfo[1]
                    val image = gameInfo[2]
                    Game(id = gameId, name = name, imageBackground = image, "Image URL", "")
                } else {
                    null
                }
            } ?: emptyList()

            favoriteGames
        }.first()
    }

}