package com.bd.multiverseexplorer.di

import android.content.Context
import com.bd.shared.core.SharedContainer
import com.bd.shared.core.createSharedContainer
import com.bd.shared.domain.usecase.GetCharacterUseCase
import com.bd.shared.domain.usecase.GetCharactersUseCase
import com.bd.shared.domain.usecase.ObserveFavoritesUseCase
import com.bd.shared.domain.usecase.ObserveIsFavoriteUseCase
import com.bd.shared.domain.usecase.ToggleFavoriteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedModule {

    @Provides
    @Singleton
    fun provideSharedContainer(
        @ApplicationContext
        context: Context
    ): SharedContainer {

        return createSharedContainer(
            context
        )
    }

    @Provides
    fun provideGetCharacterUseCase(
        container: SharedContainer
    ): GetCharacterUseCase {

        return container
            .getCharacterUseCase
    }

    @Provides
    fun provideGetCharactersUseCase(
        container: SharedContainer
    ): GetCharactersUseCase {

        return container
            .getCharactersUseCase
    }

    @Provides
    fun provideObserveFavoritesUseCase(
        container: SharedContainer
    ): ObserveFavoritesUseCase {

        return container
            .observeFavoritesUseCase
    }

    @Provides
    fun provideObserveIsFavoriteUseCase(
        container: SharedContainer
    ): ObserveIsFavoriteUseCase {

        return container
            .observeIsFavoriteUseCase
    }

    @Provides
    fun provideToggleFavoriteUseCase(
        container: SharedContainer
    ): ToggleFavoriteUseCase {

        return container
            .toggleFavoriteUseCase
    }
}