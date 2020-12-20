package dev.jameido.pokedex.domain.usecase

import dev.jameido.pokedex.data.repository.PkmnRepository

/**
 * Created by Jameido on 20/12/2020.
 */
class GetPkmnListPage(private val repository: PkmnRepository) {
    suspend operator fun invoke(page: Int) =
            repository.pkmnList(page)
}