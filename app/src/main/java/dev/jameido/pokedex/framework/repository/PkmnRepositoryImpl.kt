package dev.jameido.pokedex.framework.repository

import dev.jameido.pokedex.data.datasource.LocalPkmnDataSource
import dev.jameido.pokedex.data.datasource.NetworkPkmnDataSource
import dev.jameido.pokedex.data.mappers.PkmnDetailEntityMapper
import dev.jameido.pokedex.data.mappers.PkmnEntityMapper
import dev.jameido.pokedex.data.mappers.PkmnSpeciesEntityMapper
import dev.jameido.pokedex.data.repository.PkmnRepository
import dev.jameido.pokedex.domain.entity.*

/**
 * Created by Jameido on 17/12/2020.
 */
class PkmnRepositoryImpl(private val networkDataSource: NetworkPkmnDataSource, private val localPkmnDataSource: LocalPkmnDataSource) : PkmnRepository {

    private var listCache = hashMapOf<Int, PkmnListEntity>()
    private var listCachePageSize = 0

    private var detailCache = hashMapOf<String, PkmnDetailEntity>()
    private var speciesCache = hashMapOf<String, PkmnSpeciesEntity>()

    override suspend fun pkmnList(page: Int, pageSize: Int): PkmnListEntity {
        return readListFromDatabase(page, pageSize) ?: readListFromService(page, pageSize)!!

    }

    override suspend fun pkmnDetail(name: String): PkmnDetailEntity {
        return readDetailFromDb(name) ?: readDetailFromService(name)!!
    }

    override suspend fun pkmnSpecies(name: String): PkmnSpeciesEntity {
        return readSpeciesFromDb(name) ?: readSpeciesFromService(name)!!
    }

    //region list
    private suspend fun readListFromService(page: Int, pageSize: Int): PkmnListEntity? {
        return networkDataSource.list(pageSize, page)?.let {
            localPkmnDataSource.insertPokemon(it.results)
            val pkmnMapper = PkmnEntityMapper()
            val list = PkmnListEntity(it.next, it.previous, it.results.map { pkmn -> pkmnMapper.map(pkmn) })
            addListToCache(page, list)
            return list
        }
    }
    private suspend fun readListFromDatabase(page: Int, pageSize: Int): PkmnListEntity? {
        return localPkmnDataSource.list(pageSize, page)?.let {
            val pkmnMapper = PkmnEntityMapper()
            return PkmnListEntity(it.next, it.previous, it.results.map { pkmn -> pkmnMapper.map(pkmn) })
        }
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
    private suspend fun readDetailFromService(name: String): PkmnDetailEntity? {
        return networkDataSource.detail(name)?.let {
            localPkmnDataSource.insertDetail(it)
            val detail = PkmnDetailEntityMapper().map(it)
            addDetailToCache(name, detail)
            return detail
        }
    }

    private suspend fun readDetailFromDb(name: String): PkmnDetailEntity? {
        return localPkmnDataSource.detail(name)?.let {
            PkmnDetailEntityMapper().map(it)
        }
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
    private suspend fun readSpeciesFromService(name: String): PkmnSpeciesEntity? {
        return networkDataSource.species(name)?.let {
            localPkmnDataSource.insertSpecies(it)
            val species = PkmnSpeciesEntityMapper().map(it)
            addSpeciesToCache(name, species)
            return species
        }
    }

    private suspend fun readSpeciesFromDb(name: String): PkmnSpeciesEntity? {
        return localPkmnDataSource.species(name)?.let {
            PkmnSpeciesEntityMapper().map(it)
        }
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