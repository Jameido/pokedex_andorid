package dev.jameido.pokedex.framework.datasource.local.mappers

/**
 * Created by Jameido on 04/01/2021.
 */
interface ToDbMapper<S, T> {
    fun mapToDb(model: T): S
}