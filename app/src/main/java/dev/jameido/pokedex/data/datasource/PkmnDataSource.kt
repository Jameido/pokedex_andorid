package dev.jameido.pokedex.data.datasource

import dev.jameido.pokedex.data.models.ResPkmnDetail
import dev.jameido.pokedex.data.models.ResPkmnList
import dev.jameido.pokedex.data.models.ResPkmnSpecies

/**
 * Created by Jameido on 19/12/2020.
 */
interface PkmnDataSource {
    suspend fun list(limit: Int, offset: Int) : ResPkmnList

    suspend fun detail(name: String) : ResPkmnDetail

    suspend fun species(name: String) : ResPkmnSpecies
}

interface NetworkPkmnDataSource : PkmnDataSource

interface LocalPkmnDataSource : PkmnDataSource