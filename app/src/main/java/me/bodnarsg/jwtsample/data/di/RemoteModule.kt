package me.bodnarsg.jwtsample.data.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    //TODO: Provide remote data sources, API services, etc. here
}