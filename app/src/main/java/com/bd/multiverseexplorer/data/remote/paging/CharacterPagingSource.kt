package com.bd.multiverseexplorer.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import coil3.network.HttpException
import com.bd.multiverseexplorer.domain.model.Character
import com.bd.multiverseexplorer.data.remote.api.RickMortyApi
import com.bd.multiverseexplorer.data.mapper.toCharacter
import okio.IOException

class CharacterPagingSource(
    private val api: RickMortyApi,
    private val searchQuery: String?,
    private val status: String?
) : PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {

        val page = params.key ?: 1

        return try{
            val response = api.getCharacters(
                page = page,
                name = searchQuery,
                status =status
            )

            val characters = response.results.map{
                it.toCharacter()
            }

            LoadResult.Page(
                data = characters,
                prevKey = if(page == 1) null else page - 1,
                nextKey = if(response.info.next == null) null else page + 1
            )
        }catch (e: HttpException){
            if(e.response.code == 404){
                LoadResult.Page(
                    data = emptyList(),
                    nextKey = null,
                    prevKey = null
                )
            }else{
                LoadResult.Error(e)
            }
        }catch (e: IOException){
            LoadResult.Error(e)
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {

        val anchorPosition = state.anchorPosition ?: return  null

        val anchorPage = state.closestPageToPosition(
            anchorPosition
        )

        return anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
    }
}