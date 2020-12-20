package dev.jameido.pokedex.framework

import dev.jameido.pokedex.data.datasource.PkmnDataSource
import dev.jameido.pokedex.data.models.ResPkmnList
import dev.jameido.pokedex.data.repository.PkmnRepository

/**
 * Created by Jameido on 17/12/2020.
 */
class PkmnRepositoryImpl(private val dataSource: PkmnDataSource) : PkmnRepository {
    override suspend fun list(limit: Int, offset: Int): ResPkmnList {
        return dataSource.list(limit, offset)
    }
}