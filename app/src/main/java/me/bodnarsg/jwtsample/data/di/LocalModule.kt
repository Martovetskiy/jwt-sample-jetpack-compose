package me.bodnarsg.jwtsample.data.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.bodnarsg.jwtsample.data.repository.TokenStorageRepositoryImpl
import me.bodnarsg.jwtsample.domain.repository.TokenStorageRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Provides
    @Singleton
    fun provideSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences =
        context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    @Provides
    fun provideTokenStorageRepository(sharedPreferences: SharedPreferences): TokenStorageRepository =
        TokenStorageRepositoryImpl(sharedPreferences)
}