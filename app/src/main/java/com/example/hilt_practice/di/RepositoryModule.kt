package com.example.hilt_practice.di

import com.example.hilt_practice.network.NetworkService
import com.example.hilt_practice.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(service : NetworkService) : Repository {
        return Repository(service)
    }
}