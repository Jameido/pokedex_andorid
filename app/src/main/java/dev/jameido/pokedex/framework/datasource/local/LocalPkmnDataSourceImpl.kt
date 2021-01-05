package dev.jameido.pokedex.framework.datasource.local

import dev.jameido.pokedex.data.datasource.LocalPkmnDataSource
import dev.jameido.pokedex.data.models.PkmnDetailModel
import dev.jameido.pokedex.data.models.PkmnListModel
import dev.jameido.pokedex.data.models.PkmnModel
import dev.jameido.pokedex.data.models.PkmnSpeciesModel
import dev.jameido.pokedex.framework.datasource.local.mappers.DetailMapper
import dev.jameido.pokedex.framework.datasource.local.mappers.PkmnMapper
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
                mapped.varieties.map { it.pokemon },
                listOf(mapped.species),
                mapped.varieties.map { it.variety }
        )
    }

    override suspend fun insertPokemon(pokemon: List<PkmnModel>) {
        val mapper = PkmnMapper()
        dao.insertPkmn(pokemon.map { mapper.mapToDb(it) })
    }

    override suspend fun list(pageSize: Int, page: Int): PkmnListModel {
        TODO("Not yet implemented")
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