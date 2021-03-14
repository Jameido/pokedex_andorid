package dev.jameido.pokedex.framework.usecase

import dev.jameido.pokedex.data.repository.PkmnRepository
import dev.jameido.pokedex.domain.entity.PkmnDetailEntity
import dev.jameido.pokedex.domain.usecase.GetPkmnDetail
import javax.inject.Inject

/**
 * Created by Jameido on 03/01/2021.
 */
internal class GetPkmnDetailImpl @Inject constructor(val repository: PkmnRepository) : GetPkmnDetail {

    override suspend fun load(name: String): PkmnDetailEntity {
        return repository.pkmnDetail(name)
    }
}