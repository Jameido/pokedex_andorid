package dev.jameido.pokedex.framework.datasource.local.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "variety")
class DbPkmnSpeciesVariety(
        @PrimaryKey
        val speciesName: String,
        val pokemonName: String,
)

data class DbPkmnVariety(
        @Embedded val variety: DbPkmnSpeciesVariety,
        @Relation(
                parentColumn = "pokemonName",
                entityColumn = "name"
        )
        @Embedded val pokemon: DbPkmn,
)