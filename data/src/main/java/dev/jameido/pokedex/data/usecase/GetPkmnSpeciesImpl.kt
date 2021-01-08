package dev.jameido.pokedex.data.usecase

import dev.jameido.pokedex.data.repository.PkmnRepository
import dev.jameido.pokedex.domain.entity.PkmnSpeciesEntity
import dev.jameido.pokedex.domain.usecase.GetPkmnSpecies

/**
 * Created by Jameido on 04/01/2021.
 */
class GetPkmnSpeciesImpl(private val repository: PkmnRepository) : GetPkmnSpecies {

    override suspend fun load(name: String): PkmnSpeciesEntity {
        return repository.pkmnSpecies(name)
    }
}