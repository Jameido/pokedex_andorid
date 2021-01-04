package dev.jameido.pokedex.data.repository

import dev.jameido.pokedex.domain.entity.PkmnDetailEntity
import dev.jameido.pokedex.domain.entity.PkmnListEntity
import dev.jameido.pokedex.domain.entity.PkmnSpeciesEntity

/**
 * Created by Jameido on 17/12/2020.
 */
interface PkmnRepository {
    suspend fun pkmnList(page: Int, pageSize: Int) : PkmnListEntity

    suspend fun pkmnDetail(name: String) : PkmnDetailEntity

    suspend fun pkmnSpecies(name: String) : PkmnSpeciesEntity
}