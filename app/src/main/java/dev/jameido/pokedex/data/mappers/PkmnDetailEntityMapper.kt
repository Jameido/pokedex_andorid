package dev.jameido.pokedex.data.mappers

import dev.jameido.pokedex.data.models.PkmnDetailModel
import dev.jameido.pokedex.domain.entity.PkmnDetailEntity
import dev.jameido.pokedex.domain.entity.StatEntity

/**
 * Created by Jameido on 04/01/2021.
 */
class PkmnDetailEntityMapper(private val spriteMapper: SpriteMapper, private val statMapper: StatEntityMapper) : EntityMapper<PkmnDetailModel, PkmnDetailEntity> {

    override fun map(model: PkmnDetailModel): PkmnDetailEntity {
        val stats = model.stats.map { statMapper.map(it) }
        return PkmnDetailEntity(
                model.id,
                model.name,
                model.height * 10F,
                model.weight / 10F,
                spriteMapper.map(model.id), stats,
                model.types
        )
    }
}