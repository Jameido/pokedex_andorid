package dev.jameido.pokedex.framework.repository

import dev.jameido.pokedex.data.datasource.LocalPkmnDataSource
import dev.jameido.pokedex.data.datasource.NetworkPkmnDataSource
import dev.jameido.pokedex.data.mappers.PkmnDetailEntityMapper
import dev.jameido.pokedex.data.mappers.PkmnEntityMapper
import dev.jameido.pokedex.data.mappers.PkmnSpeciesEntityMapper
import dev.jameido.pokedex.data.models.RemotePageKey
import dev.jameido.pokedex.data.repository.PkmnRepository
import dev.jameido.pokedex.domain.entity.*

/**
 * Created by Jameido on 17/12/2020.
 */
class PkmnRepositoryImpl(private val networkDataSource: NetworkPkmnDataSource, private val localPkmnDataSource: LocalPkmnDataSource) : PkmnRepository {

    companion object {
        const val REMOTE_PAGE_SIZE = 60
        const val REMOTE_SPECIES_LIST = "species_list"
    }

    override suspend fun pkmnList(page: Int, pageSize: Int): PkmnListEntity {
        var localPage = readListFromDatabase(page, pageSize)
        if (localPage.next == null) {
            val nextRemoteKey = localPkmnDataSource.getNextRemotePageKey(REMOTE_SPECIES_LIST)
            if ((nextRemoteKey?.nextPage != null) || page == 0) {
                updateListFromNetwork(nextRemoteKey?.nextPage ?: 0, REMOTE_PAGE_SIZE)
                localPage = readListFromDatabase(page, pageSize)
            }
        }
        return localPage
    }

    override suspend fun pkmnDetail(name: String): PkmnDetailEntity {
        return readDetailFromDb(name) ?: readDetailFromService(name)!!
    }

    override suspend fun pkmnSpecies(name: String): PkmnSpeciesEntity {
        return readSpeciesFromDb(name) ?: readSpeciesFromService(name)!!
    }

    //region list
    private suspend fun readListFromDatabase(page: Int, pageSize: Int): PkmnListEntity {
        return localPkmnDataSource.list(pageSize, page)?.let {
            val pkmnMapper = PkmnEntityMapper()
            return PkmnListEntity(it.next, it.previous, it.results.map { pkmn -> pkmnMapper.map(pkmn) })
        } ?: PkmnListEntity(null, null, emptyList())
    }

    private suspend fun updateListFromNetwork(page: Int, pageSize: Int): PkmnListEntity {
        return networkDataSource.list(pageSize, page)?.let {
            localPkmnDataSource.insertPokemon(it.results)
            localPkmnDataSource.insertNextRemotePageKey(RemotePageKey(REMOTE_SPECIES_LIST, it.next))
            val pkmnMapper = PkmnEntityMapper()
            return PkmnListEntity(it.next, it.previous, it.results.map { pkmn -> pkmnMapper.map(pkmn) })
        } ?: PkmnListEntity(null, null, emptyList())
    }
    //endregion

    //region detail
    private suspend fun readDetailFromService(name: String): PkmnDetailEntity? {
        return networkDataSource.detail(name)?.let {
            localPkmnDataSource.insertDetail(it)
            return PkmnDetailEntityMapper().map(it)
        }
    }

    private suspend fun readDetailFromDb(name: String): PkmnDetailEntity? {
        return localPkmnDataSource.detail(name)?.let {
            PkmnDetailEntityMapper().map(it)
        }
    }
    //endregion


    //region species
    private suspend fun readSpeciesFromService(name: String): PkmnSpeciesEntity? {
        return networkDataSource.species(name)?.let {
            localPkmnDataSource.insertSpecies(it)
            return PkmnSpeciesEntityMapper().map(it)
        }
    }

    private suspend fun readSpeciesFromDb(name: String): PkmnSpeciesEntity? {
        return localPkmnDataSource.species(name)?.let {
            PkmnSpeciesEntityMapper().map(it)
        }
    }
    //endregion
}