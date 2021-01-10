package dev.jameido.pokedex.framework.datasource.local

import dev.jameido.pokedex.data.datasource.LocalPkmnDataSource
import dev.jameido.pokedex.data.models.*
import dev.jameido.pokedex.framework.datasource.local.mappers.DetailMapper
import dev.jameido.pokedex.framework.datasource.local.mappers.RemotePageKeyMapper
import dev.jameido.pokedex.framework.datasource.local.mappers.SpeciesElementMapper
import dev.jameido.pokedex.framework.datasource.local.mappers.SpeciesMapper
import dev.jameido.pokedex.framework.datasource.local.models.DbStat
import dev.jameido.pokedex.framework.datasource.local.models.DbType

/**
 * Created by Jameido on 05/01/2021.
 */
class LocalPkmnDataSourceImpl(val dao: PkmnDao) : LocalPkmnDataSource {
    override suspend fun insertDetail(detail: PkmnDetailModel) {
        val mapped = DetailMapper().mapToDb(detail)
        dao.insertDetail(
                listOf(mapped.detail),
                mapped.stats.map { DbStat(it.statName) },
                mapped.stats,
                mapped.types.map { DbType(it.typeName) },
                mapped.types
        )
    }

    override suspend fun insertSpecies(species: PkmnSpeciesModel) {
        val mapped = SpeciesMapper().mapToDb(species)
        dao.insertSpecies(
                mapped.speciesData,
                mapped.varieties.orEmpty().map { speciesVariety -> speciesVariety.pokemon },
                mapped.varieties.orEmpty().map { speciesVariety -> speciesVariety.variety })
    }

    override suspend fun insertPokemon(pokemon: List<PkmnModel>) {
        val mapper = SpeciesElementMapper()
        dao.insertSpeciesElement(pokemon.map { mapper.mapToDb(it) })
    }

    override suspend fun insertNextRemotePageKey(pageKey: RemotePageKey) {
        dao.insertRemotePageKey(RemotePageKeyMapper().mapToDb(pageKey))
    }

    override suspend fun getNextRemotePageKey(pageKey: String): RemotePageKey? {
        return dao.getRemotePageKey(pageKey)
    }

    override suspend fun list(pageSize: Int, page: Int): PkmnListModel? {
        val offset = page * pageSize
        val dbList = dao.getPaginatedSpecies(pageSize, offset)
        if (dbList.isNullOrEmpty()) {
            return null
        }
        val mapper = SpeciesElementMapper()
        val mappedList = dbList.map { mapper.mapFromDb(it) }
        val next = if (mappedList.size == pageSize) {
            page + 1
        } else {
            null
        }
        val previous = if (page == 0) {
            null
        } else {
            page - 1
        }
        return PkmnListModel(next, previous, mappedList)
    }

    override suspend fun detail(name: String): PkmnDetailModel? {
        return dao.getDetailByName(name)?.let {
            DetailMapper().mapFromDb(it)
        }
    }

    override suspend fun species(name: String): PkmnSpeciesModel? {
        return dao.getSpeciesByName(name)?.let {
            SpeciesMapper().mapFromDb(it)
        }
    }

}