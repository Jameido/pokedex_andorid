package dev.jameido.pokedex.data.mappers

import dev.jameido.pokedex.data.models.ResPkmnSpecies
import dev.jameido.pokedex.domain.entity.PkmnSpeciesEntity

/**
 * Created by Jameido on 04/01/2021.
 */
class PkmnSpeciesMapper : EntityMapper<ResPkmnSpecies, PkmnSpeciesEntity> {

    override fun map(model: ResPkmnSpecies): PkmnSpeciesEntity {
        val pkmnMapper = PkmnMapper()
        val varieties = model.varieties.map { pkmnMapper.map(it.pokemon) }
        val description = model.flavor_text_entries.first { it.language.name == "en" }.flavor_text
        return PkmnSpeciesEntity(model.id, model.name, description, varieties, model.evolution_chain.url)
    }
}