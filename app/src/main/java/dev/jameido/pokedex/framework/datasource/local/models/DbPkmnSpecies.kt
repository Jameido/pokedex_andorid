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

@Entity(tableName = "pokemon")
data class DbPkmn(
        val speciesName: String,
        @PrimaryKey val name: String = "",
        val url: String?
)

data class DbPkmnSpecies(
        @Embedded val speciesData: DbPkmnSpeciesData,
        @Relation(parentColumn = "name", entityColumn = "speciesName")
        val varieties: List<DbPkmn>?
)

