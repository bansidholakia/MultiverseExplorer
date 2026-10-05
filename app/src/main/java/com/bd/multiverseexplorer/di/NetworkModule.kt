package com.bd.multiverseexplorer.di

import com.bd.shared.data.repository.createCharacterRepository
import com.bd.shared.domain.repository.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideCharacterRepository():
            CharacterRepository {

        return createCharacterRepository()
    }
}