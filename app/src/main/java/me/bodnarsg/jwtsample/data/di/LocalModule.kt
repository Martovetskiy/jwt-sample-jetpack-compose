package me.bodnarsg.jwtsample.data.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    //TODO: Provide local data sources, databases, DAOs, etc. here
}