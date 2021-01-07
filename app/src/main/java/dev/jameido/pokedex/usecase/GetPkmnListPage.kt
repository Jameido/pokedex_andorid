package dev.jameido.pokedex.usecase

import androidx.paging.PagingSource
import dev.jameido.pokedex.data.repository.PkmnRepository
import dev.jameido.pokedex.domain.entity.PkmnEntity
import java.lang.Exception

/**
 * Created by Jameido on 20/12/2020.
 */
class GetPkmnListPage(private val repository: PkmnRepository) : PagingSource<Int, PkmnEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PkmnEntity> {
        val page = params.key ?: 0
        val size = params.loadSize
        return try {
            val response = repository.pkmnList(page, size)
            LoadResult.Page(
                    data = response.results,
                    prevKey = response.previous,
                    nextKey = response.next
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }
}