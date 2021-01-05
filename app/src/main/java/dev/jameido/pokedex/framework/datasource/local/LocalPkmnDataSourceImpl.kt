package dev.jameido.pokedex.framework.datasource.local

import dev.jameido.pokedex.data.datasource.LocalPkmnDataSource
import dev.jameido.pokedex.data.models.PkmnDetailModel
import dev.jameido.pokedex.data.models.PkmnListModel
import dev.jameido.pokedex.data.models.PkmnSpeciesModel
import dev.jameido.pokedex.framework.datasource.local.mappers.SpeciesMapper

/**
 * Created by Jameido on 05/01/2021.
 */
class LocalPkmnDataSourceImpl(val dao: PkmnDao) : LocalPkmnDataSource {
    override suspend fun insertDetail(detail: PkmnDetailModel) {
        TODO("Not yet implemented")
    }

    override suspend fun insertSpecies(species: PkmnSpeciesModel) {
        val mapped = SpeciesMapper().mapToDb(species)
        dao.insertSpecies(mapped.varieties.map { it.pokemon }, listOf(mapped.species), mapped.varieties.map { it.variety })
    }

    override suspend fun list(pageSize: Int, page: Int): PkmnListModel {
        TODO("Not yet implemented")
    }

    override suspend fun detail(name: String): PkmnDetailModel {
        TODO("Not yet implemented")
    }

    override suspend fun species(name: String): PkmnSpeciesModel? {
        return dao.getByName(name)?.let {
            SpeciesMapper().mapFromDb(it)
        }
    }

}