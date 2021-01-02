package dev.jameido.pokedex.domain.entity

/**
 * Created by Jameido on 20/12/2020.
 */
data class PkmnListEntity(
        val next: Int?,
        val previous: Int?,
        val results: List<PkmnEntity>
)
data class PkmnEntity(val name: String = "", val url: String?, val index: Int?, val spriteUrl: String?)