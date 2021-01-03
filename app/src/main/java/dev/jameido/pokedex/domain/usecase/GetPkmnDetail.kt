package dev.jameido.pokedex.domain.usecase

import dev.jameido.pokedex.data.repository.PkmnRepository
import dev.jameido.pokedex.domain.entity.PkmnDetailEntity

/**
 * Created by Jameido on 03/01/2021.
 */
class GetPkmnDetail(private val repository: PkmnRepository) {

    suspend fun load(name: String): PkmnDetailEntity {
        return repository.pkmnDetail(name)
    }
}