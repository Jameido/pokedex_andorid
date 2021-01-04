package dev.jameido.pokedex.framework.datasource.network.mappers

import dev.jameido.pokedex.data.models.PkmnModel
import dev.jameido.pokedex.framework.datasource.network.models.ResPkmnElement

/**
 * Created by Jameido on 04/01/2021.
 */
class PkmnMapper : ResponseMapper<ResPkmnElement, PkmnModel> {
    override fun map(model: ResPkmnElement): PkmnModel {
        return PkmnModel(model.name, model.url)
    }
}