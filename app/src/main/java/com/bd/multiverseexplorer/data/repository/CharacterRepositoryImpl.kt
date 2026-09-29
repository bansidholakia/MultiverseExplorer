package com.bd.multiverseexplorer.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import coil3.network.HttpException
import com.bd.multiverseexplorer.domain.model.Character
import com.bd.multiverseexplorer.domain.model.CharacterStatus
import com.bd.multiverseexplorer.domain.repository.CharacterRepository
import com.bd.multiverseexplorer.data.remote.api.RickMortyApi
import com.bd.multiverseexplorer.data.mapper.toCharacter
import com.bd.multiverseexplorer.data.remote.paging.CharacterPagingSource
import com.bd.multiverseexplorer.domain.model.AppError
import com.bd.multiverseexplorer.domain.model.AppResult
import kotlinx.coroutines.flow.Flow
import java.io.IOException
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val api: RickMortyApi
) : CharacterRepository {
    override suspend fun getCharacters(
        searchQuery: String,
        status: CharacterStatus
    ): Flow<PagingData<Character>> {

        val name = searchQuery
            .trim()
            .takeIf {
                it.isNotEmpty()
            }

        val statusQuery = if(status == CharacterStatus.ALL)
            null
        else
            status.name.lowercase()

        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                prefetchDistance = 5
            ),
            pagingSourceFactory = {
                CharacterPagingSource(
                    api = api,
                    searchQuery= name,
                    status = statusQuery
                )
            }
        ).flow
    }

    override suspend fun getCharacter(id: Int?): AppResult<Character> {
        return try{
            val character =
                api
                    .getCharacter(id)
                    .toCharacter()

            AppResult.Success(
                character
            )
        }catch (throwable: Throwable){
            AppResult.Error(
                throwable.toAppError()
            )
        }
    }

    private fun Throwable.toAppError():
            AppError {

        return when (this) {

            is IOException ->
                AppError.Network

            is HttpException -> {

                when (this.response.code) {

                    404 ->
                        AppError.NotFound

                    in 500..599 ->
                        AppError.Server

                    else ->
                        AppError.Unknown
                }
            }

            else ->
                AppError.Unknown
        }
    }

}