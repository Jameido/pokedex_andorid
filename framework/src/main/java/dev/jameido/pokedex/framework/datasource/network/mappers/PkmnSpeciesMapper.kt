package dev.jameido.pokedex.framework.datasource.network.mappers
import dev.jameido.pokedex.data.models.PkmnSpeciesModel
import dev.jameido.pokedex.data.models.PkmnVarietyModel
import dev.jameido.pokedex.framework.datasource.network.models.ResPkmnSpecies

/**
 * Created by Jameido on 04/01/2021.
 */
class PkmnSpeciesMapper : ResponseMapper<ResPkmnSpecies, PkmnSpeciesModel> {

    override fun map(response: ResPkmnSpecies): PkmnSpeciesModel {
        val pkmnMapper = PkmnMapper()
        val varieties = response.varieties.map { PkmnVarietyModel(pkmnMapper.map(it.pokemon), it.is_default) }
        val description = response.flavor_text_entries.first { it.language.name == "en" }.flavor_text
        return PkmnSpeciesModel(response.id, response.name, description, varieties, response.evolution_chain.url)
    }
}