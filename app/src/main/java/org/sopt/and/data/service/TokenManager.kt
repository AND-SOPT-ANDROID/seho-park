package org.sopt.and.data.service

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.sopt.and.data.service.TokenManager.Companion.DATASTORE_NAME

val Context.dataStore by preferencesDataStore(DATASTORE_NAME)

class TokenManager(private val context: Context) {
    private val TOKEN_KEY = stringPreferencesKey(TOKEN_NAME)

    fun getToken(): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[TOKEN_KEY]
        }
    }

    suspend fun saveToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    companion object {
        const val DATASTORE_NAME = "token"
        const val TOKEN_NAME = "auth_token"
    }
}