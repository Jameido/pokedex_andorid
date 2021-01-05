package dev.jameido.pokedex.framework.datasource.local.mappers

/**
 * Created by Jameido on 04/01/2021.
 */
interface DbMapper<S, T> {
    fun mapFromDb(dbEntity: S): T
    fun mapToDb(model: T): S
}