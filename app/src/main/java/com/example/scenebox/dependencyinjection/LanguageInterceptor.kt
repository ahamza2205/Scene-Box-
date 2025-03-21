package com.example.scenebox.dependencyinjection

import android.content.Context
import android.util.Log
import com.example.scenebox.sharedPreferences.PreferencesManager
import okhttp3.Interceptor
import okhttp3.Response

class LanguageInterceptor(context: Context) : Interceptor {
    private val preferencesManager = PreferencesManager(context)

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val language = preferencesManager.getSavedLanguage()

        Log.d("LanguageInterceptor", "Sending request with language: $language")

        val newRequest = originalRequest.newBuilder()
            .addHeader("Accept-Language", language)
            .build()

        return chain.proceed(newRequest)
    }
}
