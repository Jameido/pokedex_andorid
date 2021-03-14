package dev.jameido.pokedex.framework.usecase

import dev.jameido.pokedex.data.repository.PkmnRepository
import dev.jameido.pokedex.domain.entity.PkmnListPageEntity
import dev.jameido.pokedex.domain.usecase.GetPkmnListPage
import javax.inject.Inject

/**
 * Created by Jameido on 20/12/2020.
 */
internal class GetPkmnListPageImpl @Inject constructor(val repository: PkmnRepository) : GetPkmnListPage {

   override suspend fun load(query: String?, page: Int, pageSize: Int, refresh: Boolean): PkmnListPageEntity {
        return repository.pkmnList(query, page, pageSize, refresh)
    }
}