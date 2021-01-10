package dev.jameido.pokedex.framework.datasource.local.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

/**
 * Created by Jameido on 20/12/2020.
 */
@Entity(tableName = "detail")
data class DbPkmnDetailData(
        @PrimaryKey val name: String,
        val id: Int,
        val height: Float,
        val weight: Float
)

@Entity(tableName = "pokemon_stat", primaryKeys = ["pokemonName", "statName"])
data class DbPkmnStat(
        val pokemonName: String,
        val statName: String,
        val value: Int
)

@Entity(tableName = "pokemon_type", primaryKeys = ["pokemonName", "typeName"])
data class DbPkmnType(
        val pokemonName: String,
        val typeName: String,
)

data class DbPkmnDetail(
        @Embedded val detail: DbPkmnDetailData,
        @Relation(parentColumn = "name", entityColumn = "pokemonName")
        val stats: List<DbPkmnStat>,
        @Relation(parentColumn = "name", entityColumn = "pokemonName")
        val types: List<DbPkmnType>
)

