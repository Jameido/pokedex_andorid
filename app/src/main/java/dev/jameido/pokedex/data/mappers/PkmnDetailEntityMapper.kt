package dev.jameido.pokedex.data.mappers

import dev.jameido.pokedex.data.models.PkmnDetailModel
import dev.jameido.pokedex.domain.entity.PkmnDetailEntity
import dev.jameido.pokedex.domain.entity.Stat

/**
 * Created by Jameido on 04/01/2021.
 */
class PkmnDetailEntityMapper : EntityMapper<PkmnDetailModel, PkmnDetailEntity> {

    override fun map(model: PkmnDetailModel): PkmnDetailEntity {
        val stats = model.stats.map { Stat(it.value, it.name) }
        return PkmnDetailEntity(model.id, model.name, model.height * 10F, model.weight / 10F, model.sprite, stats, model.types)
    }
}