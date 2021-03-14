package dev.jameido.pokedex.framework.usecase

import dev.jameido.pokedex.data.repository.PkmnRepository
import dev.jameido.pokedex.domain.entity.PkmnSpeciesEntity
import dev.jameido.pokedex.domain.usecase.GetPkmnSpecies
import javax.inject.Inject

/**
 * Created by Jameido on 04/01/2021.
 */
internal class GetPkmnSpeciesImpl @Inject constructor(val repository: PkmnRepository) : GetPkmnSpecies {

    override suspend fun load(name: String): PkmnSpeciesEntity {
        return repository.pkmnSpecies(name)
    }
}