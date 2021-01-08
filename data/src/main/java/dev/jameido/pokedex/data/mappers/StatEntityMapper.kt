package dev.jameido.pokedex.data.mappers

import dev.jameido.pokedex.data.models.StatModel
import dev.jameido.pokedex.domain.entity.StatEntity

/**
 * Created by Jameido on 07/01/2021.
 */
class StatEntityMapper : EntityMapper<StatModel, StatEntity> {
    override fun map(model: StatModel): StatEntity {
        return StatEntity(model.value, model.name)
    }
}