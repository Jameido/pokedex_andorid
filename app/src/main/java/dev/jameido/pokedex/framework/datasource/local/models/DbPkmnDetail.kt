package dev.jameido.pokedex.framework.datasource.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Jameido on 20/12/2020.
 */
@Entity(tableName = "detail")
data class DbPkmnDetail(
        @PrimaryKey val name: String,
        val id: Int,
        val height: Float,
        val weight: Float,
        val sprite: String?
/*        val stats: List<DbStat>,
        val types: List<String>*/
)

