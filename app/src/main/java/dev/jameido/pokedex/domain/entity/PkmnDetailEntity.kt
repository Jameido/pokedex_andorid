package dev.jameido.pokedex.domain.entity

/**
 * Created by Jameido on 20/12/2020.
 */
data class PkmnDetailEntity(
        val id: Int,
        val name: String,
        val height: Int,
        val weight: Int,
        val sprite: String?,
        val stats: List<Stat>,
        val types: List<String>
)

data class Stat(
        val value: Int,
        val name: String
)