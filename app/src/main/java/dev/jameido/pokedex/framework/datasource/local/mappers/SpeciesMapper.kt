package dev.jameido.pokedex.framework.datasource.local.mappers

import dev.jameido.pokedex.data.models.PkmnModel
import dev.jameido.pokedex.data.models.PkmnSpeciesModel
import dev.jameido.pokedex.framework.datasource.local.models.*

/**
 * Created by Jameido on 05/01/2021.
 */
class SpeciesMapper : DbMapper<DbPkmnSpecies, PkmnSpeciesModel> {
    override fun mapFromDb(dbEntity: DbPkmnSpecies): PkmnSpeciesModel {
        val varieties = dbEntity.varieties.map { PkmnModel(it.pokemon.name, it.pokemon.url) }
        return PkmnSpeciesModel(dbEntity.species.id, dbEntity.species.name, dbEntity.species.description, varieties, dbEntity.species.evolutionChain)
    }

    override fun mapToDb(model: PkmnSpeciesModel): DbPkmnSpecies {
        val varieties = model.varieties.map { DbPkmnVariety(variety = DbPkmnSpeciesVariety(pokemonName =  it.name, speciesName = model.name), pokemon = DbPkmn(it.name, it.url)) }
        return DbPkmnSpecies(DbPkmnSpeciesData(model.name, model.id, model.description, model.evolutionChain), varieties = varieties)
    }
}