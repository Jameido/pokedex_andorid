package dev.jameido.pokedex.data.mappers

import dev.jameido.pokedex.data.models.ResPkmnDetail
import dev.jameido.pokedex.domain.entity.PkmnDetailEntity
import dev.jameido.pokedex.domain.entity.Stat

/**
 * Created by Jameido on 04/01/2021.
 */
class PkmnDetailMapper : EntityMapper<ResPkmnDetail, PkmnDetailEntity> {

    override fun map(model: ResPkmnDetail): PkmnDetailEntity {
        val types = model.types.map { it.type.name }
        val stats = model.stats.map { Stat(it.base_stat, it.stat.name) }
        return PkmnDetailEntity(model.id, model.name, model.height.toFloat() * 10F, model.weight.toFloat() / 10F, model.sprites.front_default, stats, types)
    }
}