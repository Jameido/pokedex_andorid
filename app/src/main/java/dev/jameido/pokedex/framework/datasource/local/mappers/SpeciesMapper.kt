package dev.jameido.pokedex.framework.datasource.local.mappers

import dev.jameido.pokedex.data.models.PkmnSpeciesModel
import dev.jameido.pokedex.framework.datasource.local.models.*

/**
 * Created by Jameido on 05/01/2021.
 */
class SpeciesMapper : DbMapper<DbPkmnSpecies, PkmnSpeciesModel> {
    override fun mapFromDb(dbEntity: DbPkmnSpecies): PkmnSpeciesModel {
        val pkmnMapper = PkmnMapper()
        val varieties = dbEntity.varieties.map { pkmnMapper.mapFromDb(it.pokemon) }
        return PkmnSpeciesModel(dbEntity.species.id, dbEntity.species.name, dbEntity.species.description, varieties, dbEntity.species.evolutionChain)
    }

    override fun mapToDb(model: PkmnSpeciesModel): DbPkmnSpecies {
        val pkmnMapper = PkmnMapper()
        val varieties = model.varieties.map { DbPkmnVariety(variety = DbPkmnSpeciesVariety(pokemonName = it.name, speciesName = model.name), pokemon = pkmnMapper.mapToDb(it)) }
        return DbPkmnSpecies(DbPkmnSpeciesData(model.name, model.id, model.description, model.evolutionChain), varieties = varieties)
    }
}