package dev.jameido.pokedex.domain.usecase

import dev.jameido.pokedex.domain.entity.PkmnListPageEntity

/**
 * Created by Jameido on 08/01/2021.
 */
interface GetPkmnListPage {
    suspend fun load(query: String?, page: Int, pageSize: Int, refresh: Boolean = false): PkmnListPageEntity
}