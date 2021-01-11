package dev.jameido.pokedex.framework.datasource.network.models

import com.squareup.moshi.JsonClass

/**
 * Created by Jameido on 04/01/2021.
 */
@JsonClass(generateAdapter = true)
data class ResPkmnSpecies(
        val id: Int,
        val name: String,
        val evolution_chain: EvolutionChain,
        val flavor_text_entries: Array<FlavorTextEntry>,
        val varieties: Array<Variety>,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ResPkmnSpecies

        if (id != other.id) return false
        if (name != other.name) return false
        if (evolution_chain != other.evolution_chain) return false
        if (!flavor_text_entries.contentEquals(other.flavor_text_entries)) return false
        if (!varieties.contentEquals(other.varieties)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + evolution_chain.hashCode()
        result = 31 * result + flavor_text_entries.contentHashCode()
        result = 31 * result + varieties.contentHashCode()
        return result
    }
}

@JsonClass(generateAdapter = true)
data class EvolutionChain(
        val url: String
)


@JsonClass(generateAdapter = true)
data class FlavorTextEntry(
    val flavor_text: String,
    val language: Language,
    val version: Version
)

@JsonClass(generateAdapter = true)
data class Language(
    val name: String
)

@JsonClass(generateAdapter = true)
data class Version(
    val name: String,
    val url: String
)

@JsonClass(generateAdapter = true)
data class Variety(
    val is_default: Boolean,
    val pokemon: ResPkmnElement
)