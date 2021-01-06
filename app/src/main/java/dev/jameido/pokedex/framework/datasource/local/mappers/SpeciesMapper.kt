package dev.jameido.pokedex.framework.datasource.local.mappers

import dev.jameido.pokedex.data.models.PkmnSpeciesModel
import dev.jameido.pokedex.framework.datasource.local.models.*

/**
 * Created by Jameido on 05/01/2021.
 */
class SpeciesMapper : DbMapper<DbPkmnSpecies, PkmnSpeciesModel> {
    override fun mapFromDb(dbEntity: DbPkmnSpecies): PkmnSpeciesModel {
        val pkmnMapper = PkmnMapper()
        val varieties = dbEntity.varieties?.map { pkmnMapper.mapFromDb(it) }
        return PkmnSpeciesModel(dbEntity.speciesData.id, dbEntity.speciesData.name, dbEntity.speciesData.description, varieties.orEmpty(), dbEntity.speciesData.evolutionChain)
    }

    override fun mapToDb(model: PkmnSpeciesModel): DbPkmnSpecies {
        val varieties = model.varieties.map { DbPkmn(model.name, it.name, it.url) }
        return DbPkmnSpecies(
                DbPkmnSpeciesData(model.name, model.id, model.description, model.evolutionChain),
                varieties)
    }
}