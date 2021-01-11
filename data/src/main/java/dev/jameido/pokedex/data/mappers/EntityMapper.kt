package dev.jameido.pokedex.data.mappers

/**
 * Created by Jameido on 08/01/2021.
 */
interface EntityMapper<S, T> {
    fun map(model: S): T
}