package com.bd.multiverseexplorer.di

import android.content.Context
import androidx.room3.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.bd.multiverseexplorer.data.local.dao.FavoriteCharacterDao
import com.bd.multiverseexplorer.data.local.database.MultiverseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): MultiverseDatabase {

        return Room
            .databaseBuilder<MultiverseDatabase>(
                context = context,
                name = "multiverse.db"
            )
            .setDriver(
                BundledSQLiteDriver()
            )
            .setQueryCoroutineContext(
                Dispatchers.IO
            )
            .build()
    }

    @Provides
    fun provideFavoriteCharacterDao(
        database: MultiverseDatabase
    ): FavoriteCharacterDao {

        return database.favoriteCharacterDao()
    }
}