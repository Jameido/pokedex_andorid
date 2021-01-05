package dev.jameido.pokedex.framework.datasource.local.mappers

import dev.jameido.pokedex.data.models.PkmnModel
import dev.jameido.pokedex.framework.datasource.local.models.DbPkmn

/**
 * Created by Jameido on 05/01/2021.
 */
class PkmnMapper : DbMapper<DbPkmn, PkmnModel> {
    override fun mapFromDb(dbEntity: DbPkmn): PkmnModel {
        return PkmnModel(dbEntity.name, dbEntity.url)
    }

    override fun mapToDb(model: PkmnModel): DbPkmn {
        return DbPkmn(model.name, model.url)
    }
}