package dev.jameido.pokedex.framework.datasource.network.mappers

import dev.jameido.pokedex.data.models.PkmnDetailModel
import dev.jameido.pokedex.data.models.Stat
import dev.jameido.pokedex.framework.datasource.network.models.ResPkmnDetail

/**
 * Created by Jameido on 04/01/2021.
 */
class PkmnDetailMapper : ResponseMapper<ResPkmnDetail, PkmnDetailModel> {

    override fun map(model: ResPkmnDetail): PkmnDetailModel {
        val types = model.types.map { it.type.name }
        val stats = model.stats.map { Stat(it.base_stat, it.stat.name) }
        return PkmnDetailModel(model.id, model.name, model.height.toFloat(), model.weight.toFloat(), model.sprites.front_default, stats, types)
    }
}