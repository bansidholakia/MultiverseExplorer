package com.bd.multiverseexplorer.di

import android.content.Context
import com.bd.shared.data.repository.createFavoriteRepository
import com.bd.shared.domain.repository.FavoriteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(
    SingletonComponent::class
)
object SharedDataModule {

    @Provides
    @Singleton
    fun provideFavoriteRepository(
        @ApplicationContext
        context: Context
    ): FavoriteRepository {

        return createFavoriteRepository(
            context
        )
    }
}