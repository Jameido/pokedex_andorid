package dev.jameido.pokedex.domain.usecase

import dev.jameido.pokedex.domain.entity.PkmnSpeciesEntity

/**
 * Created by Jameido on 08/01/2021.
 */
interface GetPkmnSpecies {

    suspend fun load(name: String): PkmnSpeciesEntity
}