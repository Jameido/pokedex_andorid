package dev.jameido.pokedex.data.mappers

import dev.jameido.pokedex.data.models.PkmnModel
import dev.jameido.pokedex.domain.entity.PkmnEntity

/**
 * Created by Jameido on 04/01/2021.
 */
class PkmnEntityMapper(private val idMapper: IdMapper, private val spriteMapper: SpriteMapper) : EntityMapper<PkmnModel, PkmnEntity> {
    override fun map(model: PkmnModel): PkmnEntity {
        return PkmnEntity(model.name, model.url, idMapper.map(model.url), spriteMapper.map(model.url))
    }
}