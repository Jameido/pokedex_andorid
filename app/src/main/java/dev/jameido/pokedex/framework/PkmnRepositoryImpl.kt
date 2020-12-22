package dev.jameido.pokedex.framework

import dev.jameido.pokedex.data.datasource.PkmnDataSource
import dev.jameido.pokedex.data.repository.PkmnRepository
import dev.jameido.pokedex.domain.entity.PkmnEntity
import dev.jameido.pokedex.domain.entity.PkmnListEntity

/**
 * Created by Jameido on 17/12/2020.
 */
class PkmnRepositoryImpl(private val dataSource: PkmnDataSource) : PkmnRepository {

    private var cache = hashMapOf<Int, PkmnListEntity>()
    private var cachePageSize = 0

    override suspend fun pkmnList(page: Int, pageSize: Int): PkmnListEntity {
        if (pageSize != cachePageSize) {
            clearCache()
            cachePageSize = pageSize
            readFromService(page, pageSize)
        }
        return readFromCache(page) ?: readFromService(page, pageSize)

    }

    private suspend fun readFromService(page: Int, pageSize: Int): PkmnListEntity {
        val offset = page * pageSize
        val response = dataSource.list(offset + pageSize, offset)
        val list = PkmnListEntity(response.next?.let { page + 1 }, response.previous?.let { page - 1 }, response.results.map { PkmnEntity(it.name, it.url) })

        addToCache(page, list)

        return list
    }

    private fun readFromCache(page: Int): PkmnListEntity? {
        if (cache.containsKey(page)) {
            return cache[page]
        }
        return null
    }

    private fun addToCache(page: Int, list: PkmnListEntity) {
        cache[page] = list
    }

    private fun clearCache() {
        cache.clear()
    }
}