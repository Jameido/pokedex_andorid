package dev.jameido.pokedex.framework.datasource.local

import dev.jameido.pokedex.framework.datasource.local.models.*

/**
 * Created by Jameido on 08/01/2021.
 */
object LocalDataSourceTestUtil {

    fun getDbPkmnSpecies(): DbPkmnSpecies {
        return DbPkmnSpecies(getDbPkmnSpeciesData(), listOf(getDbPkmnSpeciesVariety()))
    }

    fun getDbPkmnSpeciesNullVarieties(): DbPkmnSpecies {
        return DbPkmnSpecies(getDbPkmnSpeciesData(), null)
    }

    fun getDbPkmnSpeciesData(): DbPkmnSpeciesData {
        // \f has been replaced with u000C to avoid compilation issues, as reported at https://kotlinlang.org/docs/reference/basic-types.html#characters
        // The following escape sequences are supported: \t, \b, \n, \r, \', \", \\ and \$
        return DbPkmnSpeciesData("charizard", 6, "Spits fire that\nis hot enough to\nmelt boulders.\u000CKnown to cause\nforest fires\nunintentionally.", "https://pokeapi.co/api/v2/evolution-chain/2/")
    }

    fun getDbPkmnSpeciesVariety(): DbPkmnSpeciesVariety {
        return DbPkmnSpeciesVariety(getDbPkmnSpeciesVarietyData(), getDbPkmn())
    }

    fun getDbPkmnSpeciesVarietyData(): DbPkmnSpeciesVarietyData {
        return DbPkmnSpeciesVarietyData("charizard", "charizard", true)
    }

    fun getDbPkmn(): DbPkmn {
        return DbPkmn("charizard", "https://pokeapi.co/api/v2/pokemon-species/6/")
    }
}