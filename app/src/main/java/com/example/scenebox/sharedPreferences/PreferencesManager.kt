package com.example.scenebox.sharedPreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PreferencesManager (contaxt : Context) {
     private  val prefs : SharedPreferences = contaxt.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    private val _languageFlow = MutableStateFlow(getSavedLanguage())
    val languageFlow: StateFlow<String> = _languageFlow.asStateFlow()

    companion object {
        private const val KEY_SELECTED_LANGUAGE = "selected_language"
    }

    fun saveLanguage(language: String) {
        prefs.edit { putString(KEY_SELECTED_LANGUAGE, language) }
        _languageFlow.value = language
    }

    fun getSavedLanguage(): String {
        return prefs.getString(KEY_SELECTED_LANGUAGE, "en") ?: "en"
    }
}