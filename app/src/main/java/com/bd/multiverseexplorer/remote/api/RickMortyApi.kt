package com.bd.multiverseexplorer.remote.api

import com.bd.multiverseexplorer.remote.dto.CharacterDto
import com.bd.multiverseexplorer.remote.dto.CharacterResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickMortyApi{

    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int,
        @Query("name") name: String? = null,
        @Query("status") status: String? = null
    ) : CharacterResponseDto

    @GET("character/{id}")
    suspend fun getCharacter(
        @Path("id") id: Int?
    ) : CharacterDto
}