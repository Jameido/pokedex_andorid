package dev.jameido.pokedex.domain.usecase

import dev.jameido.pokedex.domain.entity.PkmnDetailEntity

/**
 * Created by Jameido on 08/01/2021.
 */
interface GetPkmnDetail {
    suspend fun load(name: String): PkmnDetailEntity
}