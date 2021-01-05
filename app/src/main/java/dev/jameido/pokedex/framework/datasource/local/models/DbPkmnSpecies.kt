package dev.jameido.pokedex.framework.datasource.local.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

/**
 * Created by Jameido on 05/01/2021.
 */
@Entity(tableName = "species")
class DbPkmnSpeciesData(
        @PrimaryKey
        val name: String,
        val id: Int,
        val description: String,
        val evolutionChain: String?
)

@Entity(tableName = "variety")
class DbPkmnSpeciesVariety(
        @PrimaryKey
        val pokemonName: String,
        val speciesName: String,
)

data class DbPkmnVariety(
        @Embedded val variety: DbPkmnSpeciesVariety,
        @Relation(parentColumn = "pokemonName", entityColumn = "name")
        val pokemon: DbPkmn,
)

data class DbPkmnSpecies(
        @Embedded val species: DbPkmnSpeciesData,
        @Relation(parentColumn = "name", entityColumn = "speciesName", entity = DbPkmnSpeciesVariety::class)
        val varieties: List<DbPkmnVariety>
)

