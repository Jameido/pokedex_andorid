package dev.jameido.pokedex.data.repository

import dev.jameido.pokedex.domain.entity.PkmnListEntity

/**
 * Created by Jameido on 17/12/2020.
 */
interface PkmnRepository {
    suspend fun pkmnList(page: Int, pageSize: Int) : PkmnListEntity
}