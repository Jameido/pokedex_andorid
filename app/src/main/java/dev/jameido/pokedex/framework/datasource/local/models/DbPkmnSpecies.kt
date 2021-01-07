package dev.jameido.pokedex.framework.datasource.local.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

/**
 * Created by Jameido on 05/01/2021.
 */
@Entity(tableName = "species")
class DbPkmnSpeciesElement(
        @PrimaryKey
        val name: String,
        val url: String?
)

@Entity(tableName = "species_data")
class DbPkmnSpeciesData(
        @PrimaryKey
        val name: String,
        val id: Int,
        val description: String,
        val evolutionChain: String?
)

@Entity(tableName = "species_variety", primaryKeys = ["pokemonName", "speciesName"])
data class DbPkmnSpeciesVarietyData(
        val pokemonName: String,
        val speciesName: String,
        val isDefault: Boolean
)

@Entity(tableName = "pokemon")
data class DbPkmn(
        @PrimaryKey val name: String = "",
        val url: String?
)

data class DbPkmnSpeciesVariety(
        @Embedded val variety: DbPkmnSpeciesVarietyData,
        @Relation(parentColumn = "pokemonName", entityColumn = "name")
        val pokemon: DbPkmn
)

data class DbPkmnSpecies(
        @Embedded val speciesData: DbPkmnSpeciesData,
        @Relation(parentColumn = "name", entityColumn = "speciesName", entity = DbPkmnSpeciesVarietyData::class)
        val varieties: List<DbPkmnSpeciesVariety>?
)

