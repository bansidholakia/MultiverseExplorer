package com.bd.multiverseexplorer.di

import com.bd.multiverseexplorer.data.remote.api.RickMortyApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://rickandmortyapi.com/api/"

    @Provides
    @Singleton
    fun provideJson() : Json{
        return Json{
            ignoreUnknownKeys= true
        }
    }

    @Provides
    @Singleton
    fun provideRetrofit(json: Json) : Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                json.asConverterFactory(
                    "application/json".toMediaType()
                )
            ).build()
    }

    @Provides
    @Singleton
    fun providerApi(retrofit: Retrofit) : RickMortyApi{
        return retrofit.create(RickMortyApi::class.java)
    }
}