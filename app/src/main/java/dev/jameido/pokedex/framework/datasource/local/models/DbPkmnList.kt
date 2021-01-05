package dev.jameido.pokedex.framework.datasource.local.models

/**
 * Created by Jameido on 05/01/2021.
 */
data class DbPkmnList(val next: Int?, val previous: Int?, val results: List<DbPkmn>)
