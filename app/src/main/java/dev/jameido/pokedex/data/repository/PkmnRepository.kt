package dev.jameido.pokedex.data.repository

import dev.jameido.pokedex.data.models.ResPkmnList

/**
 * Created by Jameido on 17/12/2020.
 */
interface PkmnRepository {
    suspend fun list(limit: Int, offset: Int) : ResPkmnList
}