package com.bd.multiverseexplorer.di

import com.bd.shared.domain.repository.CharacterRepository
import com.bd.multiverseexplorer.data.repository.CharacterRepositoryImpl
import com.bd.shared.domain.repository.FavoriteRepository
import com.bd.multiverseexplorer.data.repository.FavoriteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCharacterRepository(
        implementation: CharacterRepositoryImpl
    ) : CharacterRepository

    @Binds
    @Singleton
    abstract fun bindFavoriteRepository(
        implementation:
        FavoriteRepositoryImpl
    ): FavoriteRepository
}