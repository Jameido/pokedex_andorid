package dev.jameido.pokedex.framework.datasource.local.mappers

/**
 * Created by Jameido on 04/01/2021.
 */
interface DbMapper<S, T> : FromDbMapper<S, T>, ToDbMapper<S, T>