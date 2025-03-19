package com.example.scenebox.managers

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.LocaleList
import java.util.Locale

object LocaleManager {
    fun setLocale(context: Context, language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)

        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)
        config.setLocales(LocaleList(locale))

        context.resources.updateConfiguration(config, context.resources.displayMetrics)

        // حفظ اللغة في SharedPreferences
        saveLanguageToPreferences(context, language)
    }

    fun getSavedLanguage(context: Context): String {
        val sharedPref = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        return sharedPref.getString("selected_language", "en") ?: "en"
    }

    private fun saveLanguageToPreferences(context: Context, language: String) {
        val sharedPref = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        sharedPref.edit().putString("selected_language", language).apply()
    }
}
