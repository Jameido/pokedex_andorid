package dev.jameido.pokedex.domain.entity

/**
 * Created by Jameido on 20/12/2020.
 */
data class PkmnDetailEntity(
        val id: Int,
        val name: String,
        val sprite: String?,
        val stats: List<Stat>,
        val types: List<Type>
)
data class Type(
        val slot: Int,
        val name: String
)

data class Stat(
        val value: Int,
        val name: String
)