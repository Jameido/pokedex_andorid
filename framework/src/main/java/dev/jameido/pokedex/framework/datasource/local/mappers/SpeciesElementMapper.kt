package dev.jameido.pokedex.framework.datasource.local.mappers

import dev.jameido.pokedex.data.models.PkmnModel
import dev.jameido.pokedex.data.models.PkmnSpeciesModel
import dev.jameido.pokedex.framework.datasource.local.models.DbPkmn
import dev.jameido.pokedex.framework.datasource.local.models.DbPkmnSpeciesElement

/**
 * Created by Jameido on 05/01/2021.
 */
class SpeciesElementMapper : DbMapper<DbPkmnSpeciesElement, PkmnModel> {
    override fun mapFromDb(dbEntity: DbPkmnSpeciesElement): PkmnModel {
        return PkmnModel(dbEntity.name, dbEntity.url)
    }

    override fun mapToDb(model: PkmnModel): DbPkmnSpeciesElement {
        return DbPkmnSpeciesElement(model.name, model.url)
    }

}