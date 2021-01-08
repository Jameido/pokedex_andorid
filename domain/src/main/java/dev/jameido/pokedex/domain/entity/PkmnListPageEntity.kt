package dev.jameido.pokedex.domain.entity

/**
 * Created by Jameido on 20/12/2020.
 */
data class PkmnListPageEntity(
        val next: Int?,
        val previous: Int?,
        val results: List<PkmnEntity>
)
