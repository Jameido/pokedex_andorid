package dev.jameido.pokedex.data.usecase

import dev.jameido.pokedex.data.repository.PkmnRepository
import dev.jameido.pokedex.domain.entity.PkmnDetailEntity
import dev.jameido.pokedex.domain.usecase.GetPkmnDetail

/**
 * Created by Jameido on 03/01/2021.
 */
class GetPkmnDetailImpl(private val repository: PkmnRepository) : GetPkmnDetail {

    override suspend fun load(name: String): PkmnDetailEntity {
        return repository.pkmnDetail(name)
    }
}