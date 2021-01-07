package dev.jameido.pokedex.data.mappers

import dev.jameido.pokedex.data.models.PkmnListModel
import dev.jameido.pokedex.domain.entity.PkmnListEntity

/**
 * Created by Jameido on 04/01/2021.
 */
class PkmnListEntityMapper(private val pkmnMapper: PkmnEntityMapper) : EntityMapper<PkmnListModel, PkmnListEntity> {
    override fun map(model: PkmnListModel): PkmnListEntity {
        return PkmnListEntity(model.next, model.previous, model.results.map { pkmn -> pkmnMapper.map(pkmn) })
    }
}