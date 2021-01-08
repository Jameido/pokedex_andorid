package dev.jameido.pokedex.data

import dev.jameido.pokedex.data.models.*

/**
 * Created by Jameido on 08/01/2021.
 */
object DataTestUtil {

    fun getPokemonUrl() = "https://pokeapi.co/api/v2/pokemon/6/"
    fun getSpeciesUrl() = "https://pokeapi.co/api/v2/pokemon-species/6/"
    fun getHpStat() = StatModel("hp", 48)
    fun getAttStat() = StatModel("attack", 48)
    fun getDefStat() = StatModel("defense", 48)
    fun getSpAttStat() = StatModel("special-attack", 48)
    fun getSpDefStat() = StatModel("special-defense", 48)
    fun getSpeedStat() = StatModel("speed", 48)
    fun getNormType() = "normal"
    fun getPkmn() = PkmnModel("charizard", getSpeciesUrl())
    fun getPkmnDetail() = PkmnDetailModel(
            131,
            "ditto",
            3F,
            40F,
            listOf(
                    getHpStat(),
                    getAttStat(),
                    getDefStat(),
                    getSpAttStat(),
                    getSpDefStat(),
                    getSpeedStat()
            ),
            listOf(getNormType())
    )
    fun getPkmnSpecies() = PkmnSpeciesModel(
            6,
            "charizard",
            "Spits fire that is hot enough to melt boulders.\u000CKnown to cause forest fires unintentionally.",
            listOf(
                    PkmnVarietyModel(PkmnModel("charizard", "https://pokeapi.co/api/v2/pokemon/6/"), true),
                    PkmnVarietyModel(PkmnModel("charizard-mega-x", "https://pokeapi.co/api/v2/pokemon/10034/"), false),
                    PkmnVarietyModel(PkmnModel("charizard-mega-y", "https://pokeapi.co/api/v2/pokemon/10085/"), false),
                    PkmnVarietyModel(PkmnModel("charizard-gmax", "https://pokeapi.co/api/v2/pokemon/10187/"), false),
            ),
            "https://pokeapi.co/api/v2/evolution-chain/2/",

            )
}