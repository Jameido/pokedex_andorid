package dev.jameido.pokedex.framework.pagingsource

import androidx.paging.PagingSource
import dev.jameido.pokedex.domain.entity.PkmnEntity
import dev.jameido.pokedex.domain.usecase.GetPkmnListPage
import java.lang.Exception

/**
 * Created by Jameido on 08/01/2021.
 */
class PkmnListPagingSource(private val getListPage: GetPkmnListPage) : PagingSource<Int, PkmnEntity>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PkmnEntity> {
        val page = params.key ?: 0
        val size = params.loadSize
        return try {
            val response = getListPage.load(page, size)
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