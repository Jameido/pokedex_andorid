package dev.jameido.pokedex.framework.datasource.local.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

/**
 * Created by Jameido on 05/01/2021.
 */
data class DbPkmnSpecies(
        @Embedded val species: DbPkmnSpeciesData,
        @Relation(
                parentColumn = "name",
                entityColumn = "speciesName"
        )
        val varieties: List<DbPkmnVariety>
)

@Entity(tableName = "species")
class DbPkmnSpeciesData(
        @PrimaryKey
        val name: String,
        val id: Int,
        val description: String,
        val evolutionChain: String?
)

