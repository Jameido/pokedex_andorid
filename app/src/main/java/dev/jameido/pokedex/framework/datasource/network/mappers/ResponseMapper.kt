package dev.jameido.pokedex.framework.datasource.network.mappers

/**
 * Created by Jameido on 04/01/2021.
 */
interface ResponseMapper<S, T> {
    fun map(model: S): T
}