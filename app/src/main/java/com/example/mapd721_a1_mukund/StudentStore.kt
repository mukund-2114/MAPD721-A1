package com.example.mapd721_a1_mukund

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserStore(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("userData")
        private val ID_KEY = stringPreferencesKey("id")
        private val USER_NAME_KEY = stringPreferencesKey("username")
        private val COURSE_NAME_KEY = stringPreferencesKey("coursename")
    }

    val getID: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[ID_KEY] ?: "100"
    }

    val getUserName: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[USER_NAME_KEY] ?: "Welcome"
    }

    val getCourseName: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[COURSE_NAME_KEY] ?: ""
    }

    suspend fun saveID(id:String) {
        context.dataStore.edit { preferences ->
            preferences[ID_KEY] = id
        }
    }

    suspend fun saveUserName(username:String) {
        context.dataStore.edit { preferences ->
            preferences[USER_NAME_KEY] = username
        }
    }

    suspend fun saveCourseName(courseName:String) {
        context.dataStore.edit { preferences ->
            preferences[COURSE_NAME_KEY] = courseName
        }
    }
}