package dev.jameido.pokedex.framework

import dev.jameido.pokedex.data.datasource.PkmnDataSource
import dev.jameido.pokedex.data.mappers.PkmnDetailMapper
import dev.jameido.pokedex.data.mappers.PkmnMapper
import dev.jameido.pokedex.data.mappers.PkmnSpeciesMapper
import dev.jameido.pokedex.data.repository.PkmnRepository
import dev.jameido.pokedex.domain.entity.*

/**
 * Created by Jameido on 17/12/2020.
 */
class PkmnRepositoryImpl(private val dataSource: PkmnDataSource) : PkmnRepository {

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
        val offset = page * pageSize
        val response = dataSource.list(pageSize, offset)
        val pkmnMapper = PkmnMapper()
        val list = PkmnListEntity(response.next?.let { page + 1 }, response.previous?.let { page - 1 }, response.results.map { pkmnMapper.map(it) })
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
        val response = dataSource.detail(name)
        val detail = PkmnDetailMapper().map(response)
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
        val response = dataSource.species(name)
        val species = PkmnSpeciesMapper().map(response)
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