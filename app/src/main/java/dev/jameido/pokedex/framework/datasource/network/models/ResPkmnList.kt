package dev.jameido.pokedex.framework.datasource.network.models

import com.squareup.moshi.JsonClass

/**
 * Created by Jameido on 17/12/2020.
 */
@JsonClass(generateAdapter = true)
data class ResPkmnList(
        val count: Int,
        val next: String?,
        val previous: String?,
        val results: Array<ResPkmnElement>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ResPkmnList

        if (count != other.count) return false
        if (next != other.next) return false
        if (previous != other.previous) return false
        if (!results.contentEquals(other.results)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = count
        result = 31 * result + (next?.hashCode() ?: 0)
        result = 31 * result + (previous?.hashCode() ?: 0)
        result = 31 * result + results.contentHashCode()
        return result
    }
}

