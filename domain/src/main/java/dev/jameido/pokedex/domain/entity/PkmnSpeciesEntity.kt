package dev.jameido.pokedex.domain.entity

/**
 * Created by Jameido on 04/01/2021.
 */
class PkmnSpeciesEntity(
        val id: Int,
        val name: String,
        val description: String,
        val varieties: List<PkmnVarietyEntity>,
        val evolutionChain: String?
)