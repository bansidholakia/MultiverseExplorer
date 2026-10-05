package com.bd.shared.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bd.shared.data.mapper.toCharacter
import com.bd.shared.data.remote.api.RickMortyApi
import com.bd.shared.domain.model.Character
import io.ktor.client.plugins.ClientRequestException
import io.ktor.http.HttpStatusCode
import kotlin.coroutines.cancellation.CancellationException

internal class CharacterPagingSource(
    private val api: RickMortyApi,
    private val searchQuery: String?,
    private val status: String?
) : PagingSource<Int, Character>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Character> {

        val page =
            params.key ?: 1

        return try {

            val response =
                api.getCharacters(
                    page = page,
                    name = searchQuery,
                    status = status
                )

            LoadResult.Page(
                data =
                    response.results.map {
                        it.toCharacter()
                    },

                prevKey =
                    if (page == 1) {
                        null
                    } else {
                        page - 1
                    },

                nextKey =
                    if (
                        response.info.next == null
                    ) {
                        null
                    } else {
                        page + 1
                    }
            )

        } catch (
            exception: ClientRequestException
        ) {

            if (
                exception.response.status ==
                HttpStatusCode.NotFound
            ) {

                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )

            } else {

                LoadResult.Error(
                    exception
                )
            }

        } catch (
            exception: CancellationException
        ) {

            throw exception

        } catch (
            throwable: Throwable
        ) {

            LoadResult.Error(
                throwable
            )
        }
    }

    override fun getRefreshKey(
        state: PagingState<Int, Character>
    ): Int? {

        val anchorPosition =
            state.anchorPosition
                ?: return null

        val anchorPage =
            state.closestPageToPosition(
                anchorPosition
            )

        return anchorPage
            ?.prevKey
            ?.plus(1)
            ?: anchorPage
                ?.nextKey
                ?.minus(1)
    }
}