package dev.jameido.pokedex.framework

import dev.jameido.pokedex.data.datasource.PkmnDataSource
import dev.jameido.pokedex.data.repository.PkmnRepository
import dev.jameido.pokedex.domain.entity.PkmnEntity
import dev.jameido.pokedex.domain.entity.PkmnListEntity

/**
 * Created by Jameido on 17/12/2020.
 */
class PkmnRepositoryImpl(private val dataSource: PkmnDataSource) : PkmnRepository {

    override suspend fun pkmnList(page: Int): PkmnListEntity {
        val limit = page * PAGE_SIZE
        val response = dataSource.list(limit, limit + PAGE_SIZE)
        return PkmnListEntity(response.next?.let { page + 1 }, response.results.map { PkmnEntity(it.name, it.url) }.toTypedArray())
    }

    companion object {
        const val PAGE_SIZE = 5
    }
}