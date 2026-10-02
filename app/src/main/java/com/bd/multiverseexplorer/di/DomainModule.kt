package com.bd.multiverseexplorer.di

import com.bd.shared.domain.repository.CharacterRepository
import com.bd.shared.domain.repository.FavoriteRepository
import com.bd.shared.domain.usecase.GetCharacterUseCase
import com.bd.shared.domain.usecase.GetCharactersUseCase
import com.bd.shared.domain.usecase.ObserveFavoritesUseCase
import com.bd.shared.domain.usecase.ObserveIsFavoriteUseCase
import com.bd.shared.domain.usecase.ToggleFavoriteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    fun provideGetCharacterUseCase(
        repository: CharacterRepository
    ): GetCharacterUseCase {

        return GetCharacterUseCase(
            repository
        )
    }

    @Provides
    fun provideGetCharactersUseCase(
        repository: CharacterRepository
    ): GetCharactersUseCase {

        return GetCharactersUseCase(
            repository
        )
    }

    @Provides
    fun provideObserveIsFavoriteUseCase(
        repository: FavoriteRepository
    ): ObserveIsFavoriteUseCase {

        return ObserveIsFavoriteUseCase(
            repository
        )
    }

    @Provides
    fun provideObserveFavoritesUseCase(
        repository: FavoriteRepository
    ): ObserveFavoritesUseCase {

        return ObserveFavoritesUseCase(
            repository
        )
    }

    @Provides
    fun provideToggleFavoriteUseCase(
        repository: FavoriteRepository
    ): ToggleFavoriteUseCase {

        return ToggleFavoriteUseCase(
            repository
        )
    }
}