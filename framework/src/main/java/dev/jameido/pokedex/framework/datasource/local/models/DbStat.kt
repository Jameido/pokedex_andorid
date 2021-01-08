package dev.jameido.pokedex.framework.datasource.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Jameido on 05/01/2021.
 */
@Entity(tableName = "stat")
data class DbStat(@PrimaryKey val name: String)