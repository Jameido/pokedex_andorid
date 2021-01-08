package dev.jameido.pokedex.domain.usecase

import dev.jameido.pokedex.domain.entity.PkmnListPageEntity

/**
 * Created by Jameido on 08/01/2021.
 */
interface GetPkmnListPage {
    suspend fun load(page: Int, pageSize: Int): PkmnListPageEntity
}