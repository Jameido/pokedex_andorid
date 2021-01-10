package dev.jameido.pokedex.framework.datasource.local

import dev.jameido.pokedex.framework.datasource.local.models.*

/**
 * Created by Jameido on 08/01/2021.
 */
object LocalDataSourceTestUtil {

    fun getDbPkmnSpecies() = DbPkmnSpecies(getDbPkmnSpeciesData(), listOf(getDbPkmnSpeciesVariety()))

    fun getDbPkmnSpeciesNullVarieties() = DbPkmnSpecies(getDbPkmnSpeciesData(), null)

    fun getDbPkmnSpeciesData(): DbPkmnSpeciesData {
        // \f has been replaced with u000C to avoid compilation issues, as reported at https://kotlinlang.org/docs/reference/basic-types.html#characters
        // The following escape sequences are supported: \t, \b, \n, \r, \', \", \\ and \$
        return DbPkmnSpeciesData("charizard", 6, "Spits fire that\nis hot enough to\nmelt boulders.\u000CKnown to cause\nforest fires\nunintentionally.", "https://pokeapi.co/api/v2/evolution-chain/2/")
    }

    fun getDbPkmnSpeciesVariety() = DbPkmnSpeciesVariety(getDbPkmnSpeciesVarietyData(), getDbPkmn())

    fun getDbPkmnSpeciesVarietyData() = DbPkmnSpeciesVarietyData("charizard", "charizard", true)

    fun getDbPkmn() = DbPkmn("charizard", "https://pokeapi.co/api/v2/pokemon-species/6/")

    fun getDbPkmnSpeciesElement() = DbPkmnSpeciesElement("charizard", "https://pokeapi.co/api/v2/pokemon-species/6/")

    fun getDbPkmnDetail() = DbPkmnDetail(
            getDbPkmnDetailData(),
            listOf(
                    getPkmnHpStat(),
                    getPkmnAttStat(),
                    getPkmnDefStat(),
                    getPkmnSpAttStat(),
                    getPkmnSpDefStat(),
                    getPkmnSpeedStat()
            ),
            listOf(
                    getPkmnFireType(),
                    getPkmnFlyingType()
            )
    )

    fun getDbPkmnDetailData() = DbPkmnDetailData("charizard", 6, 170F, 90.5F)

    fun getPkmnHpStat() = DbPkmnStat("carizard", "hp", 48)
    fun getPkmnAttStat() = DbPkmnStat("carizard", "att", 48)
    fun getPkmnDefStat() = DbPkmnStat("carizard", "defense", 48)
    fun getPkmnSpAttStat() = DbPkmnStat("carizard", "special-attack", 48)
    fun getPkmnSpDefStat() = DbPkmnStat("carizard", "special-defense", 48)
    fun getPkmnSpeedStat() = DbPkmnStat("carizard", "speed", 48)

    fun getHpStat() = DbStat("hp")
    fun getAttStat() = DbStat("attack")
    fun getDefStat() = DbStat("defense")
    fun getSpAttStat() = DbStat("special-attack")
    fun getSpDefStat() = DbStat("special-defense")
    fun getSpeedStat() = DbStat("speed")

    fun getPkmnFireType() = DbPkmnType("charizard", "fire")
    fun getPkmnFlyingType() = DbPkmnType("charizard", "flying")

}