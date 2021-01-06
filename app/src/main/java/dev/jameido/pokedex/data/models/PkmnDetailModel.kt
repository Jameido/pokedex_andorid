package dev.jameido.pokedex.data.models

/**
 * Created by Jameido on 20/12/2020.
 */
data class PkmnDetailModel(
        val id: Int,
        val name: String,
        val height: Float,
        val weight: Float,
        val sprite: String?,
        val stats: List<Stat>,
        val types: List<String>
)

data class Stat(
        val name: String,
        val value: Int
)