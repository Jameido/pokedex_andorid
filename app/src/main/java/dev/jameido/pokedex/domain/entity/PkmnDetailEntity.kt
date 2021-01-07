package dev.jameido.pokedex.domain.entity

/**
 * Created by Jameido on 20/12/2020.
 */
data class PkmnDetailEntity(
        val id: Int,
        val name: String,
        val height: Float,
        val weight: Float,
        val sprite: String?,
        val stats: List<StatEntity>,
        val types: List<String>
)

data class StatEntity(
        val value: Int,
        val name: String
)