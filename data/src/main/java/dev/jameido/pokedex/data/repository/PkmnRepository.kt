package dev.jameido.pokedex.data.repository

import dev.jameido.pokedex.domain.entity.PkmnDetailEntity
import dev.jameido.pokedex.domain.entity.PkmnListPageEntity
import dev.jameido.pokedex.domain.entity.PkmnSpeciesEntity

/**
 * Created by Jameido on 17/12/2020.
 */
interface PkmnRepository {

    /**
     * Get list of pokemon species page
     */
    suspend fun pkmnList(query: String?, page: Int, pageSize: Int, refresh: Boolean = false) : PkmnListPageEntity

    /**
     * Get pokemon detail data
     */
    suspend fun pkmnDetail(name: String) : PkmnDetailEntity

    /**
     * Get pokemon species detail data
     */
    suspend fun pkmnSpecies(name: String) : PkmnSpeciesEntity
}