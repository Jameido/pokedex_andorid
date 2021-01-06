package dev.jameido.pokedex.data.mappers

import dev.jameido.pokedex.data.models.PkmnSpeciesModel
import dev.jameido.pokedex.domain.entity.PkmnSpeciesEntity

/**
 * Created by Jameido on 04/01/2021.
 */
class PkmnSpeciesEntityMapper : EntityMapper<PkmnSpeciesModel, PkmnSpeciesEntity> {

    override fun map(model: PkmnSpeciesModel): PkmnSpeciesEntity {
        val pkmnMapper = PkmnEntityMapper()
        val varieties = model.varieties.map { pkmnMapper.map(it) }
        return PkmnSpeciesEntity(model.id, model.name, model.description.replace("\n"," "), varieties, model.evolutionChain)
    }
}