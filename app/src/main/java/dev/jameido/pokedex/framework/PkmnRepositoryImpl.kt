package dev.jameido.pokedex.framework

import dev.jameido.pokedex.data.datasource.PkmnDataSource
import dev.jameido.pokedex.data.repository.PkmnRepository
import dev.jameido.pokedex.domain.entity.PkmnEntity
import dev.jameido.pokedex.domain.entity.PkmnListEntity

/**
 * Created by Jameido on 17/12/2020.
 */
class PkmnRepositoryImpl(private val dataSource: PkmnDataSource) : PkmnRepository {

    override suspend fun pkmnList(page: Int, pageSize: Int): PkmnListEntity {
        val offset = page * pageSize
        val response = dataSource.list(offset + pageSize, offset)
        return PkmnListEntity(response.next?.let { page + 1 }, response.previous?.let { page - 1 }, response.results.map { PkmnEntity(it.name, it.url) })
    }
}