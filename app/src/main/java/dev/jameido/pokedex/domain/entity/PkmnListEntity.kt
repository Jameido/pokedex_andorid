package dev.jameido.pokedex.domain.entity

/**
 * Created by Jameido on 20/12/2020.
 */
data class PkmnListEntity(
        val next: Int?,
        val results: Array<PkmnEntity>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PkmnListEntity

        if (next != other.next) return false
        if (!results.contentEquals(other.results)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = next ?: 0
        result = 31 * result + results.contentHashCode()
        return result
    }
}

data class PkmnEntity(val name: String = "", val url: String?)