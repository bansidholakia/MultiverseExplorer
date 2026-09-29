package com.bd.multiverseexplorer.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSourceFactory
import coil3.network.HttpException
import com.bd.multiverseexplorer.model.Character
import com.bd.multiverseexplorer.model.CharacterStatus
import com.bd.multiverseexplorer.remote.api.RickMortyApi
import com.bd.multiverseexplorer.remote.dto.mapper.toCharacter
import com.bd.multiverseexplorer.remote.paging.CharacterPagingSource
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val api: RickMortyApi
) : CharacterRepository{
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

    override suspend fun getCharacter(id: Int?): Character? {
        return try{
            api.getCharacter(id).toCharacter()
        }catch (e: HttpException){
            if(e.response.code == 404){
                null
            }else{
                throw e
            }
        }
    }

}