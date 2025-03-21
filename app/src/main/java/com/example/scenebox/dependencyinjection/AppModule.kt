package com.example.scenebox.dependencyinjection

import android.content.Context
import com.example.scenebox.sharedPreferences.PreferencesManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
 object AppModule {

    @Provides
    @Singleton
    fun provideApplication(@ApplicationContext app: Context): Context {
        return app
    }

    @Provides
    @Singleton
    fun providePreferencesManager(context: Context): PreferencesManager {
        return PreferencesManager(context)
    }
}
