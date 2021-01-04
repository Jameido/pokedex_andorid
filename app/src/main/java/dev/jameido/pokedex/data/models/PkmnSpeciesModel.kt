package dev.jameido.pokedex.data.models

/**
 * Created by Jameido on 04/01/2021.
 */
class PkmnSpeciesModel(
        val id: Int,
        val name: String,
        val description: String,
        val varieties: List<PkmnModel>,
        val evolutionChain: String?
)