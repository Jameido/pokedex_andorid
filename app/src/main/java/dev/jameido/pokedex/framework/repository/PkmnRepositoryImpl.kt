package dev.jameido.pokedex.framework.repository

import dev.jameido.pokedex.data.datasource.NetworkPkmnDataSource
import dev.jameido.pokedex.data.mappers.PkmnDetailEntityMapper
import dev.jameido.pokedex.data.mappers.PkmnEntityMapper
import dev.jameido.pokedex.data.mappers.PkmnSpeciesEntityMapper
import dev.jameido.pokedex.data.repository.PkmnRepository
import dev.jameido.pokedex.domain.entity.*

/**
 * Created by Jameido on 17/12/2020.
 */
class PkmnRepositoryImpl(private val networkDataSource: NetworkPkmnDataSource) : PkmnRepository {

    private var listCache = hashMapOf<Int, PkmnListEntity>()
    private var listCachePageSize = 0

    private var detailCache = hashMapOf<String, PkmnDetailEntity>()
    private var speciesCache = hashMapOf<String, PkmnSpeciesEntity>()

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
    
    override suspend fun pkmnSpecies(name: String): PkmnSpeciesEntity {
        return readSpeciesFromCache(name) ?: readSpeciesFromService(name)
    }

    //region list
    private suspend fun readListFromService(page: Int, pageSize: Int): PkmnListEntity {
        val response = networkDataSource.list(pageSize, page)
        val pkmnMapper = PkmnEntityMapper()
        val list = PkmnListEntity(response.next, response.previous, response.results.map { pkmnMapper.map(it) })
        addListToCache(page, list)

        return list
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
        val response = networkDataSource.detail(name)
        val detail = PkmnDetailEntityMapper().map(response)
        addDetailToCache(name, detail)

        return detail
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


    //region species
    private suspend fun readSpeciesFromService(name: String): PkmnSpeciesEntity {
        val response = networkDataSource.species(name)
        val species = PkmnSpeciesEntityMapper().map(response)
        addSpeciesToCache(name, species)

        return species
    }

    private fun readSpeciesFromCache(name: String): PkmnSpeciesEntity? {
        if (speciesCache.containsKey(name)) {
            return speciesCache[name]
        }
        return null
    }

    private fun addSpeciesToCache(name: String, species: PkmnSpeciesEntity) {
        speciesCache[name] = species
    }

    private fun clearSpeciesCache() {
        speciesCache.clear()
    }
    //endregion
}