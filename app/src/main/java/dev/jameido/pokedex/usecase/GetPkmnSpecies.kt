package dev.jameido.pokedex.usecase

import dev.jameido.pokedex.data.repository.PkmnRepository
import dev.jameido.pokedex.domain.entity.PkmnSpeciesEntity

/**
 * Created by Jameido on 04/01/2021.
 */
class GetPkmnSpecies(private val repository: PkmnRepository) {

    suspend fun load(name: String): PkmnSpeciesEntity {
        return repository.pkmnSpecies(name)
    }
}