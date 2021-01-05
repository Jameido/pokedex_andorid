package dev.jameido.pokedex.framework.datasource.network.mappers

import dev.jameido.pokedex.data.models.PkmnDetailModel
import dev.jameido.pokedex.data.models.Stat
import dev.jameido.pokedex.framework.datasource.network.models.ResPkmnDetail

/**
 * Created by Jameido on 04/01/2021.
 */
class PkmnDetailMapper : ResponseMapper<ResPkmnDetail, PkmnDetailModel> {

    override fun map(response: ResPkmnDetail): PkmnDetailModel {
        val types = response.types.map { it.type.name }
        val stats = response.stats.map { Stat(it.stat.name, it.base_stat) }
        return PkmnDetailModel(response.id, response.name, response.height.toFloat(), response.weight.toFloat(), response.sprites.front_default, stats, types)
    }
}