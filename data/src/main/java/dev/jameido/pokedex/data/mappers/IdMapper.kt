package dev.jameido.pokedex.data.mappers

import java.lang.NumberFormatException

/**
 * Created by Jameido on 08/01/2021.
 */
class IdMapper {
    fun map(url: String?): Int? {
        return try {
            url?.split("/")?.findLast { it.isNotBlank() }?.toInt()
        } catch (ex: NumberFormatException){
            null
        }
    }
}