package dev.jameido.pokedex.data.mappers

import dev.jameido.pokedex.data.models.PkmnListModel
import dev.jameido.pokedex.domain.entity.PkmnListPageEntity

/**
 * Created by Jameido on 04/01/2021.
 */
class PkmnListEntityMapper(private val pkmnMapper: PkmnEntityMapper) : EntityMapper<PkmnListModel, PkmnListPageEntity> {
    override fun map(model: PkmnListModel): PkmnListPageEntity {
        return PkmnListPageEntity(model.next, model.previous, model.results.map { pkmn -> pkmnMapper.map(pkmn) })
    }
}