package dev.jameido.pokedex.framework

import android.net.Uri
import dev.jameido.pokedex.data.datasource.PkmnDataSource
import dev.jameido.pokedex.data.models.PkmnElement
import dev.jameido.pokedex.data.models.ResPkmnDetail
import dev.jameido.pokedex.data.repository.PkmnRepository
import dev.jameido.pokedex.domain.entity.*

/**
 * Created by Jameido on 17/12/2020.
 */
class PkmnRepositoryImpl(private val dataSource: PkmnDataSource) : PkmnRepository {

    private var listCache = hashMapOf<Int, PkmnListEntity>()
    private var listCachePageSize = 0

    private var detailCache = hashMapOf<String, PkmnDetailEntity>()

    override suspend fun pkmnList(page: Int, pageSize: Int): PkmnListEntity {
        if (pageSize != listCachePageSize) {
            clearListCache()
            listCachePageSize = pageSize
            readListFromService(page, pageSize)
        }
        return readListFromCache(page) ?: readListFromService(page, pageSize)

    }

    override suspend fun pkmnDetail(name: String): PkmnDetailEntity {
       return readDetailFromCache(name) ?: readDetailFromService(name)
    }

    //region list
    private suspend fun readListFromService(page: Int, pageSize: Int): PkmnListEntity {
        val offset = page * pageSize
        val response = dataSource.list(pageSize, offset)
        val list = PkmnListEntity(response.next?.let { page + 1 }, response.previous?.let { page - 1 }, response.results.map { mapPkmnEntity(it) })
        addListToCache(page, list)

        return list
    }

    private fun mapPkmnEntity(element: PkmnElement): PkmnEntity {
        var index: Int? = null
        var sprite: String? = null
        element.url?.let { url ->
            index = Uri.parse(url).lastPathSegment?.toInt()
            sprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${index}.png"
        }
        return PkmnEntity(element.name, element.url, index, sprite)
    }

    private fun readListFromCache(page: Int): PkmnListEntity? {
        if (listCache.containsKey(page)) {
            return listCache[page]
        }
        return null
    }

    private fun addListToCache(page: Int, list: PkmnListEntity) {
        listCache[page] = list
    }

    private fun clearListCache() {
        listCache.clear()
    }
    //endregion


    //region detail
    private suspend fun readDetailFromService(name: String): PkmnDetailEntity {
        val response = dataSource.detail(name)
        val detail = mapPkmnDetailEntity(response)
        addDetailToCache(name, detail)

        return detail
    }

    private fun mapPkmnDetailEntity(response: ResPkmnDetail): PkmnDetailEntity {
        val types = response.types.map { it.type.name }
        val stats = response.stats.map { Stat(it.base_stat, it.stat.name) }
        return PkmnDetailEntity(response.id, response.name, response.height, response.weight, response.sprites.front_default, stats, types)
    }

    private fun readDetailFromCache(name: String): PkmnDetailEntity? {
        if (detailCache.containsKey(name)) {
            return detailCache[name]
        }
        return null
    }

    private fun addDetailToCache(name: String, detail: PkmnDetailEntity) {
        detailCache[name] = detail
    }

    private fun clearDetailCache() {
        detailCache.clear()
    }
    //endregion
}