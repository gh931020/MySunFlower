package com.peninsula.mysunflower.di

import com.peninsula.mysunflower.api.UnsplashService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetWorkModule {

    @Singleton
    @Provides
    fun provideUnsplashService(): UnsplashService{
        return UnsplashService.create()
    }

}