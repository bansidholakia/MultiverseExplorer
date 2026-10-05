package com.bd.shared.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.bd.shared.data.mapper.toCharacter
import com.bd.shared.data.remote.api.RickMortyApi
import com.bd.shared.data.remote.paging.CharacterPagingSource
import com.bd.shared.domain.model.AppError
import com.bd.shared.domain.model.AppResult
import com.bd.shared.domain.model.Character
import com.bd.shared.domain.model.CharacterStatus
import com.bd.shared.domain.repository.CharacterRepository
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.SerializationException
import kotlin.coroutines.cancellation.CancellationException

internal class CharacterRepositoryImpl(
    private val api: RickMortyApi
) : CharacterRepository {
    override fun getCharacters(
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
                    searchQuery = name,
                    status = statusQuery
                )
            }
        ).flow
    }

    override suspend fun getCharacter(id: Int?): AppResult<Character> {
        return try {

            val character =
                api
                    .getCharacter(id)
                    .toCharacter()

            AppResult.Success(
                character
            )

        } catch (
            exception: CancellationException
        ) {

            throw exception

        } catch (
            exception: ClientRequestException
        ) {

            if (
                exception.response.status ==
                HttpStatusCode.NotFound
            ) {

                AppResult.Error(
                    AppError.NotFound
                )

            } else {

                AppResult.Error(
                    AppError.Unknown
                )
            }

        } catch (
            exception: ServerResponseException
        ) {

            AppResult.Error(
                AppError.Server
            )

        } catch (
            exception: SerializationException
        ) {

            AppResult.Error(
                AppError.Unknown
            )

        } catch (
            exception: Throwable
        ) {

            AppResult.Error(
                AppError.Network
            )
        }
    }
}